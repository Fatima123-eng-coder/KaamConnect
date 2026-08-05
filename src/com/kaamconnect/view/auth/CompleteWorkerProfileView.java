package com.kaamconnect.view.auth;

import com.kaamconnect.component.AppBackButton;
import com.kaamconnect.component.AppCard;
import com.kaamconnect.component.AppLogo;
import com.kaamconnect.component.AppTextField;
import com.kaamconnect.component.ErrorLabel;
import com.kaamconnect.component.PrimaryButton;
import com.kaamconnect.component.SecondaryButton;
import com.kaamconnect.theme.AppColors;
import com.kaamconnect.theme.AppDimensions;
import com.kaamconnect.theme.AppFonts;
import com.kaamconnect.theme.UIStyles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public final class CompleteWorkerProfileView extends BorderPane {

    private final AppBackButton backButton;

    private final SecondaryButton chooseProfilePictureButton;
    private final Label profilePictureFileLabel;

    private final TextArea bioTextArea;
    private final AppTextField experienceField;
    private final AppTextField serviceAreaField;
    private final AppTextField addressField;

    private final ErrorLabel errorLabel;
    private final PrimaryButton completeProfileButton;

    public CompleteWorkerProfileView() {

        backButton =
                new AppBackButton();

        chooseProfilePictureButton =
                new SecondaryButton("Choose Profile Picture");

        profilePictureFileLabel =
                new Label("No file selected");

        bioTextArea =
                new TextArea();

        experienceField =
                new AppTextField("Enter experience in years");

        serviceAreaField =
                new AppTextField("Enter your service area");

        addressField =
                new AppTextField("Enter your complete address");

        errorLabel =
                new ErrorLabel();

        completeProfileButton =
                new PrimaryButton("Complete Profile");

        configureView();
    }

    private void configureView() {

        setPrefSize(
                AppDimensions.WINDOW_WIDTH,
                AppDimensions.WINDOW_HEIGHT
        );

        UIStyles.stylePageBackground(this);

        configureBioTextArea();
        configureExperienceField();
        configureOtherFields();
        configureProfilePictureLabel();

        /*
         * Top bar containing the back arrow and logo.
         */
        AppLogo logo =
                new AppLogo(165);

        StackPane topBar =
                new StackPane(
                        logo,
                        backButton
                );

        StackPane.setAlignment(
                backButton,
                Pos.CENTER_LEFT
        );

        topBar.setMaxWidth(
                AppDimensions.FORM_WIDTH
        );

        Label titleLabel =
                new Label("Complete Your Profile");

        titleLabel.setFont(
                AppFonts.pageTitle()
        );

        titleLabel.setTextFill(
                AppColors.TEXT_PRIMARY
        );

        Label descriptionLabel =
                new Label(
                        "Add your worker details to continue"
                );

        descriptionLabel.setFont(
                AppFonts.body()
        );

        descriptionLabel.setTextFill(
                AppColors.TEXT_SECONDARY
        );

        VBox headerBox = new VBox(
                AppDimensions.SPACING_MEDIUM,
                topBar,
                titleLabel,
                descriptionLabel
        );

        headerBox.setAlignment(
                Pos.CENTER
        );

        /*
         * Profile picture section.
         */
        Label profilePictureTitle =
                new Label("Profile Picture");

        profilePictureTitle.setFont(
                AppFonts.body()
        );

        profilePictureTitle.setTextFill(
                AppColors.TEXT_PRIMARY
        );

        HBox profilePictureRow = new HBox(
                AppDimensions.SPACING_SMALL,
                chooseProfilePictureButton,
                profilePictureFileLabel
        );

        profilePictureRow.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox.setHgrow(
                profilePictureFileLabel,
                Priority.ALWAYS
        );

        VBox profilePictureGroup = new VBox(
                AppDimensions.SPACING_SMALL,
                profilePictureTitle,
                profilePictureRow
        );

        profilePictureGroup.setMaxWidth(
                Double.MAX_VALUE
        );

        Label pictureHelpLabel = new Label(
                "Allowed: JPG, JPEG or PNG. Maximum size: 5 MB."
        );

        pictureHelpLabel.setFont(
                AppFonts.small()
        );

        pictureHelpLabel.setTextFill(
                AppColors.TEXT_SECONDARY
        );

        pictureHelpLabel.setWrapText(true);

        profilePictureGroup
                .getChildren()
                .add(pictureHelpLabel);

        /*
         * Bio section.
         */
        VBox bioGroup =
                createFieldGroup(
                        "Bio",
                        bioTextArea
                );

        Label bioHelpLabel =
                new Label(
                        "Write a short description of your skills and services."
                );

        bioHelpLabel.setFont(
                AppFonts.small()
        );

        bioHelpLabel.setTextFill(
                AppColors.TEXT_SECONDARY
        );

        bioHelpLabel.setWrapText(true);

        bioGroup.getChildren().add(
                bioHelpLabel
        );

        /*
         * Experience section.
         */
        VBox experienceGroup =
                createFieldGroup(
                        "Experience",
                        experienceField
                );

        Label experienceHelpLabel =
                new Label(
                        "Enter experience in years, for example: 5"
                );

        experienceHelpLabel.setFont(
                AppFonts.small()
        );

        experienceHelpLabel.setTextFill(
                AppColors.TEXT_SECONDARY
        );

        experienceGroup
                .getChildren()
                .add(experienceHelpLabel);

        VBox serviceAreaGroup =
                createFieldGroup(
                        "Service Area",
                        serviceAreaField
                );

        VBox addressGroup =
                createFieldGroup(
                        "Address",
                        addressField
                );

        AppCard profileCard =
                new AppCard();

        profileCard.getChildren().addAll(
                profilePictureGroup,
                bioGroup,
                experienceGroup,
                serviceAreaGroup,
                addressGroup,
                errorLabel,
                completeProfileButton
        );

        VBox pageContent = new VBox(
                AppDimensions.SPACING_LARGE,
                headerBox,
                profileCard
        );

        pageContent.setAlignment(
                Pos.TOP_CENTER
        );

        pageContent.setMaxWidth(
                AppDimensions.FORM_WIDTH
        );

        pageContent.setPadding(
                new Insets(
                        AppDimensions.SCREEN_PADDING
                )
        );

        ScrollPane scrollPane =
                new ScrollPane(pageContent);

        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setBackground(
                Background.EMPTY
        );

        scrollPane.setBorder(
                Border.EMPTY
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;"
        );

        setCenter(scrollPane);
    }

    private void configureBioTextArea() {

        bioTextArea.setPromptText(
                "Write a short bio about your skills and services"
        );

        bioTextArea.setWrapText(true);
        bioTextArea.setPrefRowCount(4);
        bioTextArea.setPrefHeight(110);
        bioTextArea.setMaxWidth(Double.MAX_VALUE);

        UIStyles.styleInput(bioTextArea);

        /*
         * Limit bio to 250 characters.
         */
        TextFormatter<String> bioFormatter =
                new TextFormatter<>(
                        change -> {

                            String newText =
                                    change.getControlNewText();

                            if (newText.length() <= 250) {
                                return change;
                            }

                            return null;
                        }
                );

        bioTextArea.setTextFormatter(
                bioFormatter
        );

        /*
         * styleInput sets the normal input height,
         * so set the larger bio height again.
         */
        bioTextArea.setPrefHeight(110);
    }

    private void configureExperienceField() {

        experienceField.setMaxWidth(
                Double.MAX_VALUE
        );

        /*
         * Allow digits only.
         */
        TextFormatter<String> experienceFormatter =
                new TextFormatter<>(
                        change -> {

                            String newText =
                                    change.getControlNewText();

                            if (newText.matches("\\d*")) {
                                return change;
                            }

                            return null;
                        }
                );

        experienceField.setTextFormatter(
                experienceFormatter
        );
    }

    private void configureOtherFields() {

        serviceAreaField.setMaxWidth(
                Double.MAX_VALUE
        );

        addressField.setMaxWidth(
                Double.MAX_VALUE
        );
    }

    private void configureProfilePictureLabel() {

        profilePictureFileLabel.setFont(
                AppFonts.small()
        );

        profilePictureFileLabel.setTextFill(
                AppColors.TEXT_SECONDARY
        );

        profilePictureFileLabel.setWrapText(true);

        chooseProfilePictureButton.setMaxWidth(
                185
        );
    }

    private VBox createFieldGroup(
            String labelText,
            Node field
    ) {

        Label label =
                new Label(labelText);

        label.setFont(
                AppFonts.body()
        );

        label.setTextFill(
                AppColors.TEXT_PRIMARY
        );

        VBox group = new VBox(
                AppDimensions.SPACING_SMALL,
                label,
                field
        );

        group.setMaxWidth(
                Double.MAX_VALUE
        );

        return group;
    }

    public void setProfilePictureFileName(
            String fileName
    ) {

        if (fileName == null ||
                fileName.isBlank()) {

            profilePictureFileLabel.setText(
                    "No file selected"
            );

        } else {

            profilePictureFileLabel.setText(
                    fileName
            );
        }
    }

    public AppBackButton getBackButton() {
        return backButton;
    }

    public SecondaryButton getChooseProfilePictureButton() {
        return chooseProfilePictureButton;
    }

    public Label getProfilePictureFileLabel() {
        return profilePictureFileLabel;
    }

    public TextArea getBioTextArea() {
        return bioTextArea;
    }

    public AppTextField getExperienceField() {
        return experienceField;
    }

    public AppTextField getServiceAreaField() {
        return serviceAreaField;
    }

    public AppTextField getAddressField() {
        return addressField;
    }

    public ErrorLabel getErrorLabel() {
        return errorLabel;
    }

    public PrimaryButton getCompleteProfileButton() {
        return completeProfileButton;
    }
}