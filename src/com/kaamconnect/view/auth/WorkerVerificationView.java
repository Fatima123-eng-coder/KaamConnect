package com.kaamconnect.view.auth;

import com.kaamconnect.component.AppCard;
import com.kaamconnect.component.AppTextField;
import com.kaamconnect.component.ErrorLabel;
import com.kaamconnect.component.PrimaryButton;
import com.kaamconnect.component.SecondaryButton;
import com.kaamconnect.model.ServiceCategory;
import com.kaamconnect.theme.AppColors;
import com.kaamconnect.theme.AppDimensions;
import com.kaamconnect.theme.AppFonts;
import com.kaamconnect.theme.UIStyles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public final class WorkerVerificationView extends BorderPane {

    private final AppTextField cnicNumberField;
    private final AppTextField cityField;
    private final AppTextField addressField;
    private final AppTextField serviceAreaField;

    private final TextArea bioArea;

    private final Spinner<Integer> experienceYearsSpinner;

    private final SecondaryButton chooseCnicFrontButton;
    private final SecondaryButton chooseCnicBackButton;

    private final Label cnicFrontFileLabel;
    private final Label cnicBackFileLabel;

    private final ListView<ServiceCategory> categoryListView;

    private final ErrorLabel errorLabel;

    private final PrimaryButton submitButton;
    private final SecondaryButton backButton;

    public WorkerVerificationView() {

        cnicNumberField =
                new AppTextField("Enter CNIC number");

        cityField =
                new AppTextField("Enter your city");

        addressField =
                new AppTextField("Enter your complete address");

        serviceAreaField =
                new AppTextField("Enter your service area");

        bioArea = new TextArea();

        experienceYearsSpinner =
                new Spinner<>(0, 50, 0);

        chooseCnicFrontButton =
                new SecondaryButton("Choose CNIC Front");

        chooseCnicBackButton =
                new SecondaryButton("Choose CNIC Back");

        cnicFrontFileLabel =
                new Label("No file selected");

        cnicBackFileLabel =
                new Label("No file selected");

        categoryListView = new ListView<>();

        errorLabel = new ErrorLabel();

        submitButton =
                new PrimaryButton("Submit Verification");

        backButton =
                new SecondaryButton("Back");

        configureView();
    }

    private void configureView() {

        setPrefSize(
                AppDimensions.WINDOW_WIDTH,
                AppDimensions.WINDOW_HEIGHT
        );

        UIStyles.stylePageBackground(this);

        configureInputs();
        configureExperienceSpinner();
        configureCategoryList();
        configureFileLabels();

        Label appNameLabel = new Label("KaamConnect");
        appNameLabel.setFont(AppFonts.heading());
        appNameLabel.setTextFill(AppColors.PRIMARY);

        Label titleLabel =
                new Label("Worker Verification");

        titleLabel.setFont(AppFonts.pageTitle());
        titleLabel.setTextFill(AppColors.TEXT_PRIMARY);

        Label descriptionLabel = new Label(
                "Complete your professional information for approval"
        );

        descriptionLabel.setFont(AppFonts.body());
        descriptionLabel.setTextFill(AppColors.TEXT_SECONDARY);
        descriptionLabel.setWrapText(true);

        VBox headerBox = new VBox(
                AppDimensions.SPACING_SMALL,
                appNameLabel,
                titleLabel,
                descriptionLabel
        );

        headerBox.setAlignment(Pos.CENTER);

        Label experienceLabel =
                createSectionLabel("Years of Experience");

        Label documentsLabel =
                createSectionLabel("CNIC Documents");

        Label categoriesLabel =
                createSectionLabel("Select Service Categories");

        HBox cnicFrontRow = createFileRow(
                chooseCnicFrontButton,
                cnicFrontFileLabel
        );

        HBox cnicBackRow = createFileRow(
                chooseCnicBackButton,
                cnicBackFileLabel
        );

        AppCard verificationCard = new AppCard();

        verificationCard.getChildren().addAll(
                cnicNumberField,
                cityField,
                addressField,
                serviceAreaField,
                bioArea,
                experienceLabel,
                experienceYearsSpinner,
                documentsLabel,
                cnicFrontRow,
                cnicBackRow,
                categoriesLabel,
                categoryListView,
                errorLabel,
                submitButton,
                backButton
        );

        VBox pageContent = new VBox(
                AppDimensions.SPACING_LARGE,
                headerBox,
                verificationCard
        );

        pageContent.setAlignment(Pos.TOP_CENTER);

        pageContent.setPadding(
                new Insets(AppDimensions.SCREEN_PADDING)
        );

        ScrollPane scrollPane = new ScrollPane(pageContent);

        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setBorder(null);

        setCenter(scrollPane);
    }

    private void configureInputs() {

        cnicNumberField.setMaxWidth(Double.MAX_VALUE);
        cityField.setMaxWidth(Double.MAX_VALUE);
        addressField.setMaxWidth(Double.MAX_VALUE);
        serviceAreaField.setMaxWidth(Double.MAX_VALUE);

        bioArea.setPromptText(
                "Write a short description about your work"
        );

        bioArea.setWrapText(true);
        bioArea.setPrefRowCount(4);
        bioArea.setPrefHeight(100);
        bioArea.setMaxWidth(Double.MAX_VALUE);

        UIStyles.styleInput(bioArea);

        // Override the normal input height because bio is multiline
        bioArea.setPrefHeight(100);
    }

    private void configureExperienceSpinner() {

        experienceYearsSpinner.setEditable(true);
        experienceYearsSpinner.setPrefHeight(
                AppDimensions.INPUT_HEIGHT
        );

        experienceYearsSpinner.setMaxWidth(Double.MAX_VALUE);
    }

    private void configureCategoryList() {

        categoryListView.getSelectionModel()
                .setSelectionMode(SelectionMode.MULTIPLE);

        categoryListView.setPrefHeight(140);
        categoryListView.setMaxWidth(Double.MAX_VALUE);

        categoryListView.setCellFactory(listView ->
                new ListCell<>() {

                    @Override
                    protected void updateItem(
                            ServiceCategory category,
                            boolean empty
                    ) {

                        super.updateItem(category, empty);

                        if (empty || category == null) {
                            setText(null);
                        } else {
                            setText(category.getCategoryName());
                        }
                    }
                }
        );
    }

    private void configureFileLabels() {

        cnicFrontFileLabel.setFont(AppFonts.small());
        cnicFrontFileLabel.setTextFill(
                AppColors.TEXT_SECONDARY
        );

        cnicBackFileLabel.setFont(AppFonts.small());
        cnicBackFileLabel.setTextFill(
                AppColors.TEXT_SECONDARY
        );

        cnicFrontFileLabel.setWrapText(true);
        cnicBackFileLabel.setWrapText(true);
    }

    private Label createSectionLabel(String text) {

        Label label = new Label(text);

        label.setFont(AppFonts.body());
        label.setTextFill(AppColors.TEXT_PRIMARY);

        return label;
    }

    private HBox createFileRow(
            SecondaryButton button,
            Label fileLabel
    ) {

        button.setMaxWidth(180);

        HBox row = new HBox(
                AppDimensions.SPACING_MEDIUM,
                button,
                fileLabel
        );

        row.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(
                fileLabel,
                Priority.ALWAYS
        );

        return row;
    }

    public void setCategories(
            List<ServiceCategory> categories
    ) {

        categoryListView.getItems().setAll(categories);
    }

    public void setCnicFrontFileName(String fileName) {

        if (fileName == null || fileName.isBlank()) {
            cnicFrontFileLabel.setText("No file selected");
        } else {
            cnicFrontFileLabel.setText(fileName);
        }
    }

    public void setCnicBackFileName(String fileName) {

        if (fileName == null || fileName.isBlank()) {
            cnicBackFileLabel.setText("No file selected");
        } else {
            cnicBackFileLabel.setText(fileName);
        }
    }

    public AppTextField getCnicNumberField() {
        return cnicNumberField;
    }

    public AppTextField getCityField() {
        return cityField;
    }

    public AppTextField getAddressField() {
        return addressField;
    }

    public AppTextField getServiceAreaField() {
        return serviceAreaField;
    }

    public TextArea getBioArea() {
        return bioArea;
    }

    public Spinner<Integer> getExperienceYearsSpinner() {
        return experienceYearsSpinner;
    }

    public SecondaryButton getChooseCnicFrontButton() {
        return chooseCnicFrontButton;
    }

    public SecondaryButton getChooseCnicBackButton() {
        return chooseCnicBackButton;
    }

    public Label getCnicFrontFileLabel() {
        return cnicFrontFileLabel;
    }

    public Label getCnicBackFileLabel() {
        return cnicBackFileLabel;
    }

    public ListView<ServiceCategory> getCategoryListView() {
        return categoryListView;
    }

    public ErrorLabel getErrorLabel() {
        return errorLabel;
    }

    public PrimaryButton getSubmitButton() {
        return submitButton;
    }

    public SecondaryButton getBackButton() {
        return backButton;
    }
}