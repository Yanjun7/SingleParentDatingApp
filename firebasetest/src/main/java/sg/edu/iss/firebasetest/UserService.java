package sg.edu.iss.firebasetest;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class UserService {

	public static final String COL_NAME = "User";

	public String saveUser(User user) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(user.getUsername())
				.set(user);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	//get full user list
	public List<User> getUsers() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		CollectionReference usersList = dbFirestore.collection(COL_NAME);
		ApiFuture<QuerySnapshot> future = usersList.get();
		
		QuerySnapshot result = future.get();
	
		
		List<User> users = null;
		
		if (!result.isEmpty()) {
			
			users = result.toObjects(User.class);
			return users;
			
		} else {
			return null;
		}
	}
	
	public User getUserByfullName(String fullName) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		CollectionReference usersList = dbFirestore.collection(COL_NAME);
		ApiFuture<QuerySnapshot> future = usersList.get();
		
		QuerySnapshot result = future.get();
	
		List<User> users = null;
		User user = null;
		
		if (!result.isEmpty()) {
			
			users = result.toObjects(User.class);
			
			user = users.stream().filter(x -> x.getFullName().equals(fullName)).findFirst().get();
			
			return user;
			
		} else {
			return null;
		}
	}
	
	//the user's username is the userId/documentId
	public User getUser(String username) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(username);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		
		User user = null;
		
		if (document.exists()) {
			user = document.toObject(User.class);
			return user;
		} else {
			return null;
		}
	}

	public String updateUser(User user) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(user.getUsername())
				.set(user);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	public String deleteUser(String username) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(username).delete();
		return "Document with username " + username + " has been deleted";
	}
}
