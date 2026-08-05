package com.kaamconnect.view.auth;

import com.kaamconnect.component.AppBackButton;
import com.kaamconnect.component.AppCard;
import com.kaamconnect.component.AppLogo;
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
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.util.List;

public final class WorkerVerificationView extends BorderPane {

    private final AppBackButton backButton;

    private final AppTextField cnicNumberField;
    private final AppTextField cityField;

    private final SecondaryButton chooseCnicFrontButton;
    private final SecondaryButton chooseCnicBackButton;

    private final Label cnicFrontFileLabel;
    private final Label cnicBackFileLabel;

    private final ComboBox<ServiceCategory> categoryComboBox;

    private final ErrorLabel errorLabel;
    private final PrimaryButton submitButton;

    public WorkerVerificationView() {

        backButton = new AppBackButton();

        cnicNumberField =
                new AppTextField("35202-1234567-1");

        cityField =
                new AppTextField("Enter your city");

        chooseCnicFrontButton =
                new SecondaryButton("Choose CNIC Front");

        chooseCnicBackButton =
                new SecondaryButton("Choose CNIC Back");

        cnicFrontFileLabel =
                new Label("No file selected");

        cnicBackFileLabel =
                new Label("No file selected");

        categoryComboBox =
                new ComboBox<>();

        errorLabel =
                new ErrorLabel();

        submitButton =
                new PrimaryButton("Submit Application");

        configureView();
    }

    private void configureView() {

        setPrefSize(
                AppDimensions.WINDOW_WIDTH,
                AppDimensions.WINDOW_HEIGHT
        );

        UIStyles.stylePageBackground(this);

        configureCategoryComboBox();
        configureFileLabel(cnicFrontFileLabel);
        configureFileLabel(cnicBackFileLabel);

        AppLogo logo =
                new AppLogo(165);

        StackPane topBar =
                new StackPane(logo, backButton);

        StackPane.setAlignment(
                backButton,
                Pos.CENTER_LEFT
        );

        Label titleLabel =
                new Label("Worker Verification");

        titleLabel.setFont(AppFonts.pageTitle());
        titleLabel.setTextFill(AppColors.TEXT_PRIMARY);

        Label descriptionLabel =
                new Label("Submit your details for admin approval");

        descriptionLabel.setFont(AppFonts.body());
        descriptionLabel.setTextFill(AppColors.TEXT_SECONDARY);

        VBox headerBox = new VBox(
                AppDimensions.SPACING_MEDIUM,
                topBar,
                titleLabel,
                descriptionLabel
        );

        headerBox.setAlignment(Pos.CENTER);

        VBox cnicGroup =
                createFieldGroup(
                        "CNIC Number",
                        cnicNumberField
                );

        HBox frontRow =
                createFileRow(
                        chooseCnicFrontButton,
                        cnicFrontFileLabel
                );

        HBox backRow =
                createFileRow(
                        chooseCnicBackButton,
                        cnicBackFileLabel
                );

        VBox frontGroup =
                createFieldGroup(
                        "Upload CNIC Front",
                        frontRow
                );

        VBox backGroup =
                createFieldGroup(
                        "Upload CNIC Back",
                        backRow
                );

        VBox cityGroup =
                createFieldGroup(
                        "City",
                        cityField
                );

        VBox categoryGroup =
                createFieldGroup(
                        "Service Category",
                        categoryComboBox
                );

        Label categoryHelp =
                new Label("Select only one category");

        categoryHelp.setFont(AppFonts.small());
        categoryHelp.setTextFill(AppColors.TEXT_SECONDARY);

        categoryGroup.getChildren().add(categoryHelp);

        Label fileHelp =
                new Label("Allowed: JPG, JPEG or PNG. Maximum size: 5 MB.");

        fileHelp.setFont(AppFonts.small());
        fileHelp.setTextFill(AppColors.TEXT_SECONDARY);
        fileHelp.setWrapText(true);

        AppCard card =
                new AppCard();

        card.getChildren().addAll(
                cnicGroup,
                frontGroup,
                backGroup,
                fileHelp,
                cityGroup,
                categoryGroup,
                errorLabel,
                submitButton
        );

        VBox content = new VBox(
                AppDimensions.SPACING_LARGE,
                headerBox,
                card
        );

        content.setAlignment(Pos.TOP_CENTER);
        content.setMaxWidth(AppDimensions.FORM_WIDTH);

        content.setPadding(
                new Insets(AppDimensions.SCREEN_PADDING)
        );

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setBackground(Background.EMPTY);
        scrollPane.setBorder(Border.EMPTY);

        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;"
        );

        setCenter(scrollPane);
    }

    private void configureCategoryComboBox() {

        categoryComboBox.setPromptText(
                "Select service category"
        );

        UIStyles.styleComboBox(categoryComboBox);

        categoryComboBox.setConverter(
                new StringConverter<>() {

                    @Override
                    public String toString(
                            ServiceCategory category
                    ) {

                        if (category == null) {
                            return "";
                        }

                        return category.getCategoryName();
                    }

                    @Override
                    public ServiceCategory fromString(
                            String value
                    ) {
                        return null;
                    }
                }
        );
    }

    private void configureFileLabel(Label label) {

        label.setFont(AppFonts.small());
        label.setTextFill(AppColors.TEXT_SECONDARY);
        label.setWrapText(true);
    }

    private HBox createFileRow(
            SecondaryButton button,
            Label fileLabel
    ) {

        button.setMaxWidth(175);

        HBox row = new HBox(
                AppDimensions.SPACING_SMALL,
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

    private VBox createFieldGroup(
            String labelText,
            Node field
    ) {

        Label label =
                new Label(labelText);

        label.setFont(AppFonts.body());
        label.setTextFill(AppColors.TEXT_PRIMARY);

        VBox group = new VBox(
                AppDimensions.SPACING_SMALL,
                label,
                field
        );

        group.setMaxWidth(Double.MAX_VALUE);

        return group;
    }

    public void setCategories(
            List<ServiceCategory> categories
    ) {
        categoryComboBox.getItems().setAll(categories);
    }

    public void setCnicFrontFileName(String name) {
        cnicFrontFileLabel.setText(
                name == null || name.isBlank()
                        ? "No file selected"
                        : name
        );
    }

    public void setCnicBackFileName(String name) {
        cnicBackFileLabel.setText(
                name == null || name.isBlank()
                        ? "No file selected"
                        : name
        );
    }

    public AppBackButton getBackButton() {
        return backButton;
    }

    public AppTextField getCnicNumberField() {
        return cnicNumberField;
    }

    public AppTextField getCityField() {
        return cityField;
    }

    public SecondaryButton getChooseCnicFrontButton() {
        return chooseCnicFrontButton;
    }

    public SecondaryButton getChooseCnicBackButton() {
        return chooseCnicBackButton;
    }

    public ComboBox<ServiceCategory> getCategoryComboBox() {
        return categoryComboBox;
    }

    public ErrorLabel getErrorLabel() {
        return errorLabel;
    }

    public PrimaryButton getSubmitButton() {
        return submitButton;
    }
}