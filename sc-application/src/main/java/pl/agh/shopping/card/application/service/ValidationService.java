package pl.agh.shopping.card.application.service;

import org.springframework.stereotype.Service;
import pl.agh.shopping.card.application.dto.ShoppingCardRequestDTO;
import pl.agh.shopping.card.common.exception.CustomException;
import pl.agh.shopping.card.common.util.FieldName;

import static pl.agh.shopping.card.common.util.ValidationUtil.validateNotNull;


@Service
public class ValidationService {

    public void validate(ShoppingCardRequestDTO shoppingCardRequestDTO) throws CustomException {
        validateNotNull(FieldName.USERNAME, shoppingCardRequestDTO.getUsername());
    }
}
