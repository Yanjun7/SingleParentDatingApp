package sg.edu.iss.firebasetest.comment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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
public class CommentService {

	public static final String COL_NAME = "Comment";

	public String saveForum(Comment comment) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<DocumentReference> collectionsApiFuture = dbFirestore.collection(COL_NAME).add(comment);
		return "Comment " + collectionsApiFuture.get().getId().toString() + " saved.";
	}

	//get all comments by 
	public List<String> getCommentsByUserId(String userId) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		CollectionReference collectionReference = dbFirestore.collection(COL_NAME);
		ApiFuture<QuerySnapshot> future = collectionReference.get();
		QuerySnapshot document = future.get();
		
		if (!document.isEmpty()) {
			List<Comment> allComments = document.toObjects(Comment.class);
			List<Comment> comments = allComments.stream().filter(x -> x.getUserId().equals(userId)).collect(Collectors.toList());
			
			List<String> text = new ArrayList<>();
			
			comments.forEach(x -> {
				text.add(x.getBody());
			});
			
			return text;
		} else {
			return null;
		}
	}

	public String updateForum(Comment comment) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(comment.getCommentId())
				.set(comment);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	public String deleteForum(String commentId) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(commentId).delete();
		return "Comment with id " + commentId + " has been deleted";
	}
}
