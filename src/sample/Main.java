package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Vector;

public class Main extends Application {
    private final int WIDTH = 800;
    private final int HEIGHT = 500;

    // variables
    private ListView departmentsListView;
    private ListView courseListView;
    private int selectedIndex;
    private Vector<Course> storage;
    // entry elements
    private ComboBox departmentEntry;
    private TextField courseNumber;
    private TextField courseName;
    private TextField courseCredits;
    private Label departmentError;
    private Label numberError;
    private Label nameError;
    private Label creditError;



    @Override
    public void start(Stage mainStage) {

        storage = new Vector<>();

        mainStage = initStage();
        mainStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }


    private Stage initStage(){
        /**
         * Considering the repetitive code of setting up these ui element,
         * I think it could & should be extracted into set up function.
         * However, this is homework assignment and won't need to be modified or expanded.
         * So I'm just putting it all out, organized by notes.
         */

        Stage mainStage = new Stage(); // create the stage

        Group actors = new Group();    // create our actors
        Scene mainScene = new Scene(actors, WIDTH, HEIGHT, Color.ORANGE); // set the scene

        selectedIndex = -1; // set the slected index to an out-of-bounds number. Courses will only load is index is >0

        // DEPARTMENT BOX
        // Set up and add label for the Department box
        Label departmentsLabel = new Label("Departments:");
        departmentsLabel.setLayoutX(05);
        departmentsLabel.setLayoutY(05);
        actors.getChildren().add(departmentsLabel);
        // Setup the listView for the department box
        departmentsListView = new ListView();
        departmentsListView.setLayoutX(5);
        departmentsListView.setLayoutY(25);
        departmentsListView.setMaxHeight(160);
        departmentsListView.setMaxWidth(150);
        for (String s : Course.DEPARTMENTS){
            departmentsListView.getItems().add(s);
        }
        actors.getChildren().add(departmentsListView);
        // when an entry is clicked the index will change to it
        departmentsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent){
                selectedIndex = departmentsListView.getSelectionModel().getSelectedIndex();

            }
        });

        // COURSE BOX
        // Set up and label the course list
        Label coursesLabel = new Label("Courses");
        coursesLabel.setLayoutX(300);
        coursesLabel.setLayoutY(05);
        actors.getChildren().add(coursesLabel);
        // course list view
        courseListView = new ListView();
        courseListView.setLayoutX(300);
        courseListView.setLayoutY(25);
        courseListView.setMaxHeight(160);
        courseListView.setMaxWidth(300);
        actors.getChildren().add(courseListView);

        // BUTTONS
        // create the display department button
        Button departmentButton = new Button("Display Selected");
        departmentButton.setWrapText(true);
        departmentButton.setLayoutX(170);
        departmentButton.setLayoutY(25);
        actors.getChildren().add(departmentButton);
        // event handler for my button
        departmentButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                int index = departmentsListView.getSelectionModel().getSelectedIndex();
                courseListView.getItems().clear();
                for (Course c: storage) {
                    if (c.getDepartment() == Course.DEPARTMENTS[index]){
                        courseListView.getItems().add(c.toString());
                    }
                }
            }
        });
        // create our display all button
        Button displayAllButton = new Button("Display All");
        displayAllButton.setWrapText(true);
        displayAllButton.setLayoutX(170);
        displayAllButton.setLayoutY(75);
        actors.getChildren().add(displayAllButton);
        // event handler for my button
        displayAllButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                courseListView.getItems().clear();
                for (Course c: storage) {
                    courseListView.getItems().add(c);
                }
            }
        });
        // create our Save button
        Button saveButton = new Button("Save Course");
        saveButton.setWrapText(true);
        saveButton.setLayoutX(170);
        saveButton.setLayoutY(125);
        actors.getChildren().add(saveButton);
        // event handler for my button
        saveButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                //first clear the error codes
                departmentError.setText("");
                numberError.setText("");
                nameError.setText("");
                creditError.setText("");

                if ((departmentEntry.getValue() != null ) &&
                        (!courseNumber.getText().equals("")) &&
                        (!courseName.getText().equals("")) &&
                        (!courseCredits.getText().equals(""))){
                    int index = departmentEntry.getSelectionModel().getSelectedIndex();
                    storage.add(new Course(
                            Course.DEPARTMENTS[index],
                            Course.DEP_CODES[index],
                            courseNumber.getText(),
                            courseName.getText(),
                            courseCredits.getText()));
                    courseNumber.setText("");
                    courseName.setText("");
                    courseCredits.setText("");

                }
                else{
                    if (departmentEntry.getValue() == null){
                        departmentError.setText("Error: Please select a department.");
                    }
                    if (courseNumber.getText().equals("")) {
                        numberError.setText("Error: Please enter the course number.");
                    }
                    if (courseName.getText().equals("")) {
                        nameError.setText("Error: Please enter the course name.");
                    }
                    if (courseCredits.getText().equals("")) {
                        creditError.setText("Error: Please enter the course credits.");
                    }

                }
            }
        });


        // COURSE ENTRY
        // Set up and add label course entry
        Label courseEntryLabel = new Label("Add Course to Catalog \nCourse Department: ");
        courseEntryLabel.setLayoutX(05);
        courseEntryLabel.setLayoutY(190);
        actors.getChildren().add(courseEntryLabel);

        departmentEntry = new ComboBox();
        departmentEntry.setLayoutX(05);
        departmentEntry.setLayoutY(225);
        departmentEntry.getSelectionModel().select(0);
        for (String s: Course.DEPARTMENTS){
            departmentEntry.getItems().add(s);
        }
        actors.getChildren().add(departmentEntry);
        // department entry error messege label
        departmentError = new Label("");
        departmentError.setLayoutX(160);
        departmentError.setLayoutY(225);
        actors.getChildren().add(departmentError);

        // Set up and add label course entry
        Label courseNumberLabel = new Label("Course Number: ");
        courseNumberLabel.setLayoutX(05);
        courseNumberLabel.setLayoutY(250);
        actors.getChildren().add(courseNumberLabel);
        // Now for the course number text field
        courseNumber = new TextField("");
        courseNumber.setLayoutX(05);
        courseNumber.setLayoutY(265);
        actors.getChildren().add(courseNumber);
        // number entry error messege label
        numberError = new Label("");
        numberError.setLayoutX(160);
        numberError.setLayoutY(265);
        actors.getChildren().add(numberError);

        // Set up and add label course entry
        Label courseNameLabel = new Label("Course Name: ");
        courseNameLabel.setLayoutX(05);
        courseNameLabel.setLayoutY(300);
        actors.getChildren().add(courseNameLabel);
        // Name text field
        courseName = new TextField("");
        courseName.setLayoutX(05);
        courseName.setLayoutY(315);
        actors.getChildren().add(courseName);
        // name entry error messege label
        nameError = new Label("");
        nameError.setLayoutX(160);
        nameError.setLayoutY(315);
        actors.getChildren().add(nameError);

        // Set up and add label course entry
        Label courseCreditsLabel = new Label("Course Credit Quantity: ");
        courseCreditsLabel.setLayoutX(05);
        courseCreditsLabel.setLayoutY(350);
        actors.getChildren().add(courseCreditsLabel);
        // Credit text field
        courseCredits = new TextField("");
        courseCredits.setLayoutX(05);
        courseCredits.setLayoutY(365);
        actors.getChildren().add(courseCredits);
        // creditError entry error messege label
        creditError = new Label("");
        creditError.setLayoutX(160);
        creditError.setLayoutY(365);
        actors.getChildren().add(creditError);

        // EXIT Button
        Button exit = new Button("Exit");
        exit.setLayoutX(300);
        exit.setLayoutY(400);
        actors.getChildren().add(exit);
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });


        // Handle everything for the stage
        mainStage.setTitle("Department Courses");
        mainStage.setWidth(WIDTH);
        mainStage.setHeight(HEIGHT);
        mainStage.setScene(mainScene); // add our only scene

        return mainStage;
    }
}