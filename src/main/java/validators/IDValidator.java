package validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("idValidator")
public class IDValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String idValue = o.toString();
        try {
            if (idValue.matches("^[1-9]\\d*$")) {
                int id = Integer.parseInt(idValue);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            FacesMessage facesMessage = new FacesMessage("ID must be a positive int number!");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(facesMessage);
        }
    }
}
