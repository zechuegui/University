from sklearn.datasets import load_iris

from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import confusion_matrix
iris_dataset = load_iris()

X_train, X_test, y_train, y_test = train_test_split(iris_dataset['data'], iris_dataset['target'], random_state=0)

knn = KNeighborsClassifier(n_neighbors=1)

print("Com {} exemplos\n", len(X_train))

knn.fit(X_train, y_train)
pred_knn = knn.predict(X_test)
confusion = confusion_matrix(y_test, pred_knn)
print("Confusion matrix:\n{}".format(confusion))
print("Test set accuracy: {:.2f}".format(knn.score(X_test, y_test)))

from sklearn.utils import resample

nSamples = [100,80,60,40,20,10,5,1]

for i in nSamples:
    
    newX_train, newY_train = resample(X_train, y_train, n_samples=i, random_state=0)
    

    new_knn = KNeighborsClassifier(n_neighbors=1)
    new_knn.fit(newX_train,newY_train)
    pred_new_knn = new_knn.predict(X_test)
    confusion = confusion_matrix(y_test, pred_new_knn)

    print(f"\nCom {len(newX_train)} exemplos")
    
    print("Confusion matrix:\n{}".format(confusion))
    print("Test set accuracy: {:.2f}".format(new_knn.score(X_test, y_test)))






