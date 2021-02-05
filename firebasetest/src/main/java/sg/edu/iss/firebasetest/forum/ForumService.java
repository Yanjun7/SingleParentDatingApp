package sg.edu.iss.firebasetest.forum;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ForumService {

	public static final String COL_NAME = "Forum";

	public String saveForum(Forum forum) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(forum.getName())
				.set(forum);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	//the user's username is the userId/documentId
	public Forum getForum(String name) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		
		Forum forum = null;
		
		if (document.exists()) {
			forum = document.toObject(Forum.class);
			return forum;
		} else {
			return null;
		}
	}

	public String updateForum(Forum forum) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(forum.getName())
				.set(forum);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	public String deleteForum(String name) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();
		return "Forum with category " + name + " has been deleted";
	}
}
