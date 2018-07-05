package avaliador.server.window;

//import java.util.ArrayList;

import avaliador.server.window.abstractions.IStage;
//import avaliador.universal.factories.QuestionContainerFactory;
import avaliador.universal.factories.SceneFactory;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ServerCreateWindow implements IStage{
	
	@FXML private TextField questionnaireTextField;
	@FXML private TextField authorTextField;
	@FXML private Text totalPointsText;
	
	@FXML private ScrollPane questionScrollPane;
	@FXML private VBox questionVBox;
	
	@FXML private Button addQuestionButton;
	@FXML private Button deleteQuestionButton;
	
	//private ArrayList<QuestionContainer> questionContainers;
	
	private Stage stage;
	private Scene scene;
	private SceneFactory sceneFactory;
	//private QuestionContainerFactory questionFactory;
	
	public ServerCreateWindow() {
		stage = new Stage();
		//questionContainers = new ArrayList<QuestionContainer>();
		//questionFactory = new QuestionContainerFactory();
	}

	@Override
	public void startStage() {
		setStage("appearance/ServerCreateStageWindow.fxml");
		stage.setTitle("Criador de Questoes");
		stage.setResizable(false);
		stage.show();
	}
	@Override
	public void setStage(String stagePath) {
		sceneFactory = new SceneFactory();
		this.scene = sceneFactory.buildScene(this, stagePath);
		this.stage.setScene(this.scene);
	}
	
	@FXML
	public void createButtonManager() {
		// TEMPORARY DEBUG METHOD BODY. MUST CHANGE LATER.
		
		IStage newQuestionWindow = new ServerNewQuestionWindow();
		newQuestionWindow.startStage();
		
		/*
		questionContainers.add(questionFactory.buildQuestionContainer(questionVBox));
		questionVBox.getChildren().add(questionContainers.get(questionContainers.size() - 1).getContainerPane());
		*/
	}
	
	@FXML
	public void saveButtonManager() {
		// TO BE ADDED
	}

}