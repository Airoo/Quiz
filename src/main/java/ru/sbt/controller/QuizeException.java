package ru.sbt.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Runtime exception
 */
public class QuizeException extends RuntimeException {

	private static final long serialVersionUID = -6409987363668059842L;

	public QuizeException(String msg){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("A runtime exception occured");
		alert.setContentText(msg);
		alert.showAndWait();
	}
}
