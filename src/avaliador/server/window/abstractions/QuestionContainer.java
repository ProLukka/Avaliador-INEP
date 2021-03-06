package avaliador.server.window.abstractions;

import java.io.IOException;

import avaliador.server.window.ServerNewQuestionWindow;
import avaliador.universal.abstractions.IContainer;
import avaliador.universal.enums.ErrorType;
import avaliador.universal.enums.QuestionType;
import avaliador.universal.managers.AlertManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public abstract class QuestionContainer implements IContainer{
	
	@FXML protected AnchorPane containerPane;
	
	@FXML protected TextField qValueField;
	@FXML protected TextArea qStatementArea;
	
	@FXML protected ToggleGroup answerGroup;
	@FXML protected RadioButton aRadio;
	@FXML protected RadioButton bRadio;
	@FXML protected RadioButton cRadio;
	@FXML protected RadioButton dRadio;
	@FXML protected RadioButton eRadio;
	
	@FXML protected Button addQuestionButton;
	
	protected ServerNewQuestionWindow parentWindow;
	
	public QuestionContainer(ServerNewQuestionWindow parentWindow, Parent parent, String fxmlPath) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		loader.setController(this);
		parent = loader.load();
		this.parentWindow = parentWindow;
		this.qStatementArea.setWrapText(true);
	}
	
	@FXML
	public void addQuestion() {
		if(isQuestionValid()) {
			parentWindow.getPointerContainer().getQuestionnaireWindow().createQuestionContainer(parentWindow.getPointerContainer());
			parentWindow.getStage().hide();
			parentWindow.setMenuDisable(true);
			this.setButtonDisable(true);
			updateQuestion();
			parentWindow.getStage().setOnCloseRequest(e -> updateQuestion());
		}
	}
	
	public String getQuestionTitle() {
		return qStatementArea.getText();
	}
	
	public void setQuestionTitle(String title) {
		qStatementArea.setText(title);
	}
	
	public double getQuestionValue() {
		return Double.parseDouble(qValueField.getText());
	}
	
	public void setQuestionValue(double value) {
		qValueField.setText(Double.toString(value));
	}
	
	public char getAnswer() {
		if(aRadio.isSelected()) { return 'A'; }
		else if(bRadio.isSelected()) { return 'B'; }
		else if(cRadio.isSelected()) { return 'C'; }
		else if(dRadio.isSelected()) { return 'D'; }
		else{ return 'E'; }
	}
	
	public void setAnswer(char ans) {
		if(ans == 'A') aRadio.setSelected(true);
		else if(ans == 'B') bRadio.setSelected(true);
		else if(ans == 'C') cRadio.setSelected(true);
		else if(ans == 'D') dRadio.setSelected(true);
		else if(ans == 'E') eRadio.setSelected(true);
	}
	
	protected void setButtonDisable(boolean isDisabled) {
		addQuestionButton.setDisable(isDisabled);
	}
	
	public abstract QuestionType getQuestionType();
	public abstract String[] getAnswersText();
	public abstract boolean isInputCorrect();
	
	protected void alertMessage(ErrorType error) {
		AlertManager alert = new AlertManager();
		alert.alertWarningMessage("Problemas na Quest�o!", error);
	}
	
	protected boolean isQuestionValid() {
		if(isQuestionTitleValid()) {
			if(isValueValid()) {
				return isInputCorrect();
			}
			else alertMessage(ErrorType.INVALIDGRADE);
		}
		else alertMessage(ErrorType.EMPTYTITLE);
		return false;
	}
	
	protected boolean isQuestionTitleValid() {
		if(!qStatementArea.getText().isEmpty()) {
			return true;
		}
		else return false;
	}
	
	protected boolean isValueValid() {
		if(!qValueField.getText().isEmpty()) {
			String regex = "^\\d+(\\.\\d+)?";
			if(qValueField.getText().matches(regex)) {
				return true;
			}
		}
		return false;
	}
	
	protected void updateQuestion() {
		parentWindow.getPointerContainer().setQuestionTitle(qStatementArea.getText());
		parentWindow.getPointerContainer().setQuestionValue(getQuestionValue());
		parentWindow.getPointerContainer().getQuestionnaireWindow().updateQuestionnaireValue();
	}

}
