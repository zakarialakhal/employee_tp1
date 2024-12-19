package controller;

import view.*;
import dao.*;
import model.*;
import enums.*;
import java.util.List;
import javax.swing.JOptionPane;

public class EmployerController {

    private final FormFrame frame;
    private final EmployerLogic employerLogic;

    public EmployerController(FormFrame frame, EmployerLogic employerLogic) {
        this.frame = frame;
        this.employerLogic = employerLogic;

        frame.getBtnPanel().getAddBtn().addActionListener(addEvent -> addEmployer());
        frame.getBtnPanel().getUpdateBtn().addActionListener(updateEvent -> updateEmployer());
        frame.getBtnPanel().getRemoveBtn().addActionListener(deleteEvent -> deleteEmployer());
        frame.getBtnPanel().getEditBtn().addActionListener(editEvent -> fillFieldsWithSelectedEmployer());

        loadEmployers();
    }

    private void addEmployer() {
        try {
            if (employerLogic.addEmployer(
                    1,
                    frame.getInPanel().getFirstNameField().getText(),
                    frame.getInPanel().getLastNameField().getText(),
                    frame.getInPanel().getEmailField().getText(),
                    Integer.parseInt(frame.getInPanel().getTelephoneNumberField().getText()),
                    Double.parseDouble(frame.getInPanel().getSalaryField().getText()),
                    Role.valueOf(frame.getInPanel().getSelectedRole().toString()),
                    Poste.valueOf(frame.getInPanel().getSelectedPoste().toString())
            )) {
                JOptionPane.showMessageDialog(frame, "Employer added successfully.");
                loadEmployers();
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to add employer.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid input: " + e.getMessage());
        }
    }

    private void updateEmployer() {
        try {
            if (employerLogic.updateEmployer(
                    frame.getListPanel().getSelectedRowId(),
                    frame.getInPanel().getFirstNameField().getText(),
                    frame.getInPanel().getLastNameField().getText(),
                    frame.getInPanel().getEmailField().getText(),
                    Integer.parseInt(frame.getInPanel().getTelephoneNumberField().getText()),
                    Double.parseDouble(frame.getInPanel().getSalaryField().getText()),
                    Role.valueOf(frame.getInPanel().getSelectedRole().toString()),
                    Poste.valueOf(frame.getInPanel().getSelectedPoste().toString())
            )) {
                JOptionPane.showMessageDialog(frame, "Employer updated successfully.");
                loadEmployers();
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to update employer.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid input: " + e.getMessage());
        }
    }

    private void deleteEmployer() {
        try {
            if (employerLogic.deleteEmployer(frame.getListPanel().getSelectedRowId())) {
                JOptionPane.showMessageDialog(frame, "Employer deleted successfully.");
                loadEmployers();
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to delete employer.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid input: " + e.getMessage());
        }
    }

    private void fillFieldsWithSelectedEmployer() {
        try {
            int selectedRowId = frame.getListPanel().getSelectedRowId();
            if (selectedRowId != -1) {
                Employer selectedEmployer = employerLogic.getEmployerById(selectedRowId);
                if (selectedEmployer != null) {
                    frame.getInPanel().getFirstNameField().setText(selectedEmployer.getFirstName());
                    frame.getInPanel().getLastNameField().setText(selectedEmployer.getLastName());
                    frame.getInPanel().getEmailField().setText(selectedEmployer.getEmail());
                    frame.getInPanel().getTelephoneNumberField()
                            .setText(String.valueOf(selectedEmployer.getPhoneNumber()));
                    frame.getInPanel().getSalaryField()
                            .setText(String.valueOf(selectedEmployer.getSalary()));
                    frame.getInPanel().setSelectedRole(selectedEmployer.getRole());
                    frame.getInPanel().setSelectedPoste(selectedEmployer.getPoste());
                } else {
                    JOptionPane.showMessageDialog(frame, "Employer not found.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select an employer to edit.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
        }
    }

    private void loadEmployers() {
        frame.getListPanel().updateEmployerList(employerLogic.getAllEmployers());
    }
}
