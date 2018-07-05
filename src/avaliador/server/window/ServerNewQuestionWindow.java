package avaliador.server.window;

import avaliador.server.window.abstractions.IContainer;
import avaliador.server.window.abstractions.IStage;
import avaliador.universal.factories.ContainerFactory;
import avaliador.universal.factories.SceneFactory;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ServerNewQuestionWindow implements IStage{
	
	@FXML private AnchorPane mainPane;
	@FXML private SplitMenuButton menuMain;
	@FXML private MenuItem qUniqueMenu;
	@FXML private AnchorPane containerPane;
	
	private Stage stage;
	private Scene scene;
	private SceneFactory sceneFactory;
	private IContainer questionContainer;
	
	public ServerNewQuestionWindow() {
		stage = new Stage();
	}

	@Override
	public void startStage() {
		setStage("appearance/ServerNewQuestionStageWindow.fxml");
		stage.setTitle("Criador de Questoes - Nova Questao");
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
	public void setUniqueQuestion() {
		containerPane.getChildren().clear();
		ContainerFactory containerFactory = new ContainerFactory();
		questionContainer = containerFactory.buildContainer(containerPane, "appearance/containers/SimpleQuestionContainer.fxml");
		containerPane.getChildren().setAll(questionContainer.getContainer());
		stage.sizeToScene();
	}

}
