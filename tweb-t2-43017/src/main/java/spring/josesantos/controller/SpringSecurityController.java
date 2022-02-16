package spring.josesantos.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.User;


import spring.josesantos.dao.UserDao;
import spring.josesantos.model.Utilizador;

import spring.josesantos.dao.EventoDao;
import spring.josesantos.dao.InscricaoDao;
import spring.josesantos.model.Evento;
import spring.josesantos.model.Inscricao;
import spring.josesantos.model.Pagamento;
import spring.josesantos.dao.PagamentoDao;


import spring.josesantos.dao.TempoDao;
import spring.josesantos.model.Tempo;

import spring.josesantos.rowmapper.PagamentoRowMapper;


@Controller
public class SpringSecurityController {

	static final String ERROR = "error";
	static final String UTILIZADOR = "utilizador";

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Autowired
    private UserDao userDao;

	@Autowired
    private EventoDao eventoDao;

	@Autowired
    private InscricaoDao inscricaoDao;

	@Autowired
    private TempoDao tempoDao;

	@Autowired
    private PagamentoDao pagamentoDao;




	public Utilizador getCurrentUtilizador(){
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 		

		if(user instanceof User){ // Significa que está autenticado
			
			User authUser = (User) user;
			return userDao.getUser(authUser.getUsername()); // Neste caso o username é o email (pk)
		}
		return null;
	}


	@GetMapping("/")
	public String defaultPage(Model model) {

		Utilizador currentUtilizador = getCurrentUtilizador();
		if(currentUtilizador != null){
			model.addAttribute(UTILIZADOR, currentUtilizador);
		}

		return "home";
	}

	@GetMapping("/login")
	public String loginPage( 
		 Model model, 
		@RequestParam(value = ERROR, required = false) String error,
		@RequestParam(value = "logout", required = false) String logout,
		@RequestParam(value = "success", required = false) String success) {
		if (error != null) {
			model.addAttribute(ERROR, "Erro nas credenciais inseridas");
		}
		if (logout != null) {
			return "home";
		}
		return "login";
	}

	@GetMapping("/logout")
	public String logoutPage(Model model, HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/login?logout";
	}

	@GetMapping("/admin")
	public String adminPage(Model model) {
		
		model.addAttribute("title", "Administrator Control Panel");
		model.addAttribute("message", "This page demonstrates how to use Spring security");
		return "admin";
	}

	// Regista um novo utilizador
	@PostMapping("/register")
	public String register( 
		@RequestParam String username,
		@RequestParam String password,
		@RequestParam String email1,
		@RequestParam String email2,
		RedirectAttributes attrs){
		
		if(email1.equals(email2)){ 

			int emailIgual = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM UTILIZADORES WHERE email = ?", Integer.class, email1);

			if( emailIgual == 0){ // Não existe nenhum email igual
				
				String encodedPassword= new BCryptPasswordEncoder().encode(password);

				Utilizador utilizador = new Utilizador(username, encodedPassword, email1, "ROLE_ATLETA");
				userDao.saveUser(utilizador);

				return "redirect:/login";
			}

			// Se os e-mails coincidem mas já existe
			attrs.addFlashAttribute(ERROR, "E-mail já está a ser utilizado");	
			return "redirect:/signup";
		}	

		// Se os e-mails não coincidem
		attrs.addFlashAttribute(ERROR, "E-mails devem ser iguais");
		return "redirect:/signup";	
	}

	@GetMapping("/signup")
	public String signup(Model model){
		
		model.addAttribute(ERROR);
		return "signup";
	}

	@GetMapping("/registarParticipante")
	public String registarParticipate(Model model){

		Utilizador utilizador = getCurrentUtilizador();
		if(utilizador != null){
			model.addAttribute(UTILIZADOR, utilizador);
		}

		return "registarParticipante";
	}

	// Insere um participante na BD
	@PostMapping("/inserirParticipante")
	public ResponseEntity<String> inserirParticipante(  
		@RequestParam String evento,
		@RequestParam String genero,
		@RequestParam String escalao){

		Utilizador currentUtilizador = getCurrentUtilizador();
		if(currentUtilizador != null){

			int idEvento = Integer.parseInt(evento);

			// Verificar se já existe uma inscrição deste utilizador neste evento
			int inscrito = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM inscricoes WHERE email = ? and id = ?", 
				Integer.class, currentUtilizador.getEmail(), idEvento);

			if(inscrito == 1){ // Significa que o utilizador já está inscrito nesta prova

				return ResponseEntity.ok("{\"status\": \"erro\", \"content\": \"NAO BOM\"}");
			}

			// Se o utilizador ainda não está inscrito neste evento, adiciona-lo
			Inscricao inscricao = new Inscricao(currentUtilizador.getEmail(),genero, escalao,idEvento, 0, 0);
			inscricaoDao.saveInscricao(inscricao);

			return ResponseEntity.ok("{\"status\": \"ok\", \"content\": \"nice\"}");
		}

		return ResponseEntity.ok("{\"status\": \"erro\", \"content\": \"Algo wrong\"}");
	}

	@GetMapping("/registarEvento")
	public String registarEvento(Model model){

		Utilizador currentUtilizador = getCurrentUtilizador();
		if(currentUtilizador != null){
			model.addAttribute(UTILIZADOR, currentUtilizador);
		}

		return "registarEvento";
	}

	// Insere um evento na BD (id é pk, mas o nome também tem de ser único)
	@PostMapping("/inserirEvento")
	public ResponseEntity<String> inserirEvento(
		@RequestParam String nomeEvento,
		@RequestParam Date data,
		@RequestParam int preco,
		@RequestParam String descricao){

		int eventoRepetido = jdbcTemplate.queryForObject(
			"SELECT COUNT(*) FROM evento where nomeEvento = ?", 
			Integer.class, nomeEvento);	
			
		if(eventoRepetido != 0){  // O Evento inserido já existe
			return ResponseEntity.ok("{\"status\": \"erro\", \"msg\": \"Evento inserido já existe, utilize outro nome\"}");
		}

		int nId = jdbcTemplate.queryForObject(  // Não é necessário incrementar o nº do Id porque começa em 0
			"SELECT COUNT(*) FROM evento", 
			Integer.class);	
	
		Evento evento = new Evento(nId, nomeEvento, data, preco, descricao);
		eventoDao.saveEvento(evento);

		return ResponseEntity.ok("{\"status\": \"ok\", \"msg\": \"Evento inserido com sucesso\"}");
	}  

	// Devolve uma lista com todos os eventos
	@PostMapping("/getEventos")
	public ResponseEntity<List<Evento>> getEventos(){
		return ResponseEntity.ok(eventoDao.getEventoList());
	}

	@GetMapping("verInscricoes")
	public String verInscricoes(Model model){

		Utilizador currentUtilizador = getCurrentUtilizador();

		if(currentUtilizador != null){

			model.addAttribute(UTILIZADOR, currentUtilizador);
		}

		return "verInscricoes";
	}

	// Devolve uma lista com todos os eventos onde o utilizador com o email dado está inscrito
	@PostMapping("/getInscricoesByEmail")
	public ResponseEntity<List<Inscricao>> getInscricoes(@RequestParam String email){

		return ResponseEntity.ok(inscricaoDao.getInscricoesUtilizador(email));
	}

	// Inserir pagamento na BD
	@PostMapping("/inserirPagemento")
	public ResponseEntity<Boolean> inserirPagamento(
		@RequestParam String email,
		@RequestParam int id,
		@RequestParam float mbAmount,
		@RequestParam Integer mbEntity,
		@RequestParam Integer mbReference){

		System.out.println("Pagemento antes");
		System.out.println(pagamentoDao.getTodosPagamentos());

		Pagamento pagamento = new Pagamento(email, id, mbAmount, mbEntity, mbReference);
		pagamentoDao.savePagamento(pagamento);

		System.out.println("Pagemento depois");
		System.out.println(pagamentoDao.getTodosPagamentos());


		return ResponseEntity.ok(true);
	}

	@PostMapping("/pagarEvento")
	public ResponseEntity<Integer> pagarEvento(
		@RequestParam Integer mbReference,
		@RequestParam String email,
		@RequestParam Integer id
	){

		System.out.println("inscricoes antes");
		System.out.println(inscricaoDao.getTodasInscricoes());

		// Verificar se a referencia inserida existe
		int refExiste = jdbcTemplate.queryForObject("select count(*) from pagamento where mb_reference = ?;", 
			new Integer[]{mbReference}, Integer.class);

		if(refExiste == 1){

			// Remover do pagamento
			jdbcTemplate.execute("DELETE FROM pagamento where mb_reference = '"+mbReference+"';");

			// Receber o nº de dorsais existente para a prova (Teremos de incrementar visto começar a 1)
			int nDorsal = jdbcTemplate.queryForObject(
				"select count(dorsal) from inscricoes where dorsal is not 0 and id=?", new Integer[]{id} ,Integer.class) + 1;

			// Atualizar a inscrição, colocando o pago a true e o nº do dorsal
			jdbcTemplate.update("UPDATE inscricoes set pago = 1, dorsal ="+nDorsal+" where id ="+id+" and email='"+email+"';");		
			

			System.out.println("inscricoes depois");
			System.out.println(inscricaoDao.getTodasInscricoes());

			return ResponseEntity.ok(nDorsal);
		}

		return ResponseEntity.ok(0);
	}

	// Dado um email e um id, devolve os dados de pagamento associados
	@PostMapping("/getDadosPagamento")
	public ResponseEntity<Pagamento> getDadosPagamento(
		@RequestParam String email,
		@RequestParam Integer id
	){
		Pagamento pagamento = jdbcTemplate.queryForObject(
			"Select * from pagamento where email = ? and id = ?",
			 new Object[]{email, id}, new PagamentoRowMapper());

		return ResponseEntity.ok(pagamento); 
	}

	// Dado o nome de um evento devolve o id do mesmo
	@PostMapping("/getEventoIdByName")
	public ResponseEntity<Integer> getEventoIdByName(@RequestParam String nomeEvento){

		return ResponseEntity.ok(eventoDao.getIdByName(nomeEvento));
	}

	// Dado um ID devolve o evento associado
	@PostMapping("/getEventoById")
	public ResponseEntity<Evento> getEventoById(@RequestParam Integer id){

		return ResponseEntity.ok(eventoDao.getEvento(id));
	}

	@GetMapping("/registarTempo")
	public String registarTempo(Model model){

		Utilizador currentUtilizador = getCurrentUtilizador();
		if(currentUtilizador != null){
			model.addAttribute(UTILIZADOR, currentUtilizador);
		}

		return "registarTempo";
	}

	@PostMapping("/saveTime")
	public ResponseEntity<String> saveTime(
		@RequestParam int evento, // neste evento é o id do evento
		@RequestParam int dorsal,
		@RequestParam String local,
		@RequestParam String tempo
	){

		System.out.println("tempos antes");
		System.out.println(tempoDao.getTodosTempos());

		// Verificar se o dorsal introduzido existe
		int dorsalExiste = jdbcTemplate.queryForObject(
			"select count(*) from inscricoes where dorsal=? and id=?;", new Object[]{dorsal, evento}, Integer.class);
		
		if(dorsalExiste == 0){  // Se não existir
			return ResponseEntity.ok("dorsalNaoExiste");
		}

		// Verificar se já existe um tempo associado para os valores inseridos
		int tempoExiste = jdbcTemplate.queryForObject(
			"select count(*) from tempos where id=? and dorsal=? and ponto=?;", 
			new Object[]{evento, dorsal, local}, Integer.class);

		if(tempoExiste != 0){ // Significa que existe
			return ResponseEntity.ok("tempoExiste");
		}

		Tempo tempoBd = new Tempo(dorsal, evento, local, tempo);
		tempoDao.saveTempo(tempoBd);

		System.out.println("tempos depois");
		System.out.println(tempoDao.getTodosTempos());

		return ResponseEntity.ok("ok");
	}

	@GetMapping("/procurarEvento")
	public String procurarEvento(Model model){

		Utilizador currentUtilizador = getCurrentUtilizador();
		if(currentUtilizador != null){
			model.addAttribute(UTILIZADOR, currentUtilizador);
		}

		return "procurarEvento";
	}


	@GetMapping("/procurarEvento/informacaoEvento")
	public String informacaoEvento(Model model){

		Utilizador currentUtilizador = getCurrentUtilizador();
		if(currentUtilizador != null){
			model.addAttribute(UTILIZADOR, currentUtilizador);
		}

		model.addAttribute("id");
		model.addAttribute("tipo");
		model.addAttribute("nomeEvento");

		return "informacaoEvento";
	}

	@PostMapping("/informacaoEvento")
	public String informacaoEvento(@RequestParam String nomeEvento, @RequestParam String tipo, RedirectAttributes attrs){

		int id = eventoDao.getIdByName(nomeEvento);
		attrs.addFlashAttribute("nomeEvento", nomeEvento);
		attrs.addFlashAttribute("id", id);
		attrs.addFlashAttribute("tipo", tipo);

	
		return "redirect:/procurarEvento/informacaoEvento";
	}

	// Dado o id do evento devolve os Dorsais Nomes genero escalão e Estado de todos os inscritos
	@PostMapping("/getCommonInformationByEventId")
	public ResponseEntity<List<String>> getCommonInformationByEventId(@RequestParam int id){

		List<String> resultArray = new ArrayList<>();

		String sql = "Select * from inscricoes where id="+id;

		List<Map<String,Object>> result = jdbcTemplate.queryForList(sql);

		String jsonString;
		for(int i = 0; i<result.size(); i++){

			String nomeUtilizador = userDao.getUser(result.get(i).get("EMAIL").toString()).getUsername();
			jsonString = "{\"dorsal\":"+result.get(i).get("DORSAL").toString()+
						 ",\"name\":\""+nomeUtilizador+
						 "\",\"genero\":\""+result.get(i).get("GENERO").toString()+
						 "\",\"escalao\":\""+result.get(i).get("ESCALAO").toString()+
						 "\",\"pago\":"+result.get(i).get("PAGO").toString()+"}";


			resultArray.add(jsonString);			
		}

		return ResponseEntity.ok(resultArray);
	}

	// Devolve todos os tempos associados à prova com o id dado
	@PostMapping("/getTempoById")
	public ResponseEntity<List<Tempo>> getTempoById(@RequestParam int id){

		return ResponseEntity.ok(tempoDao.getTemposById(id));
	}
}
