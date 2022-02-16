import numpy as np
from sklearn import preprocessing
from sklearn.ensemble import RandomForestRegressor


train = np.genfromtxt("train.csv", delimiter=",", dtype=None, encoding=None)
x_train = train[1:,0:-1]     
y_train = train[1:,-1]


test = np.genfromtxt("test.csv", delimiter=",", dtype=None, encoding=None)
idEstudante = test [1:,0]
x_test = test[1:,0:]


teste = preprocessing.LabelEncoder() # É necessário fazer um labelEnconder na coluna Program
x_train[:,1] = teste.fit_transform(x_train[:,1])
x_test[:,1] = teste.fit_transform(x_test[:,1])

regressor = RandomForestRegressor(n_estimators=100,criterion='mse',min_samples_leaf=2,ccp_alpha=0.00001,random_state=0)
# tentar mesmos valores de alfa mas sem warm_start, ver se da melhor
regressor.fit(x_train, y_train)

y_pred = regressor.predict(x_test) # Fazer o predict co

y_pred = np.round(y_pred,0) # Arredondar os valores para números inteiros
y_pred = y_pred.astype(int) # Mudar o tipo de float para inteiro


final = np.stack([idEstudante,y_pred], axis=1)

final = np.insert(final,0,["Id","Failure"],axis=0) # Adicionar o cabeçalho à matrix final

np.savetxt("submission.csv", final,delimiter=',' ,fmt='%s') # Colocar a matriz no ficheiro .csv para a submissão