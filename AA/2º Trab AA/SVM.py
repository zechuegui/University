import numpy as np
from sklearn import preprocessing
from sklearn.svm import SVC

train = np.genfromtxt("train.csv", delimiter=",", dtype=None, encoding=None)
x_train = train[1:,1:-1]     
y_train = train[1:,-1]


test = np.genfromtxt("test.csv", delimiter=",", dtype=None, encoding=None)
idEstudante = test [1:,0]
f_test = test[1:,1:]


teste = preprocessing.LabelEncoder() # É necessário fazer um labelEnconder na coluna Program
x_train[:,0] = teste.fit_transform(x_train[:,0])
f_test[:,0] = teste.fit_transform(f_test[:,0])

svm = SVC(kernel='rbf', C=1.0, gamma='auto')

svm.fit(x_train, y_train)

y_pred = svm.predict(f_test)

final = np.stack([idEstudante,y_pred], axis=1)

final = np.insert(final,0,["Id","Failure"],axis=0) # Adicionar o cabeçalho à matrix final

np.savetxt("submission.csv", final,delimiter=',' ,fmt='%s') # Colocar a matriz no ficheiro .csv para a submissão