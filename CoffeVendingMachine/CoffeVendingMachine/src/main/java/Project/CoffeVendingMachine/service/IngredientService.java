package Project.CoffeVendingMachine.service;

import Project.CoffeVendingMachine.entity.Ingredient;
import Project.CoffeVendingMachine.repository.IngredientRepository;
import Project.CoffeVendingMachine.request.QuantityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class IngredientService
{
    @Autowired
    IngredientRepository ingredientRepository;

    public ResponseEntity<List<Ingredient>> availableIngredients() // To see all the available ingredients
    {
        //Find all the ingredients in repository
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    public ResponseEntity<Object> availableIngredientAt(int id)
    {
        //find the ingredient by id
        Optional<Ingredient> optionIngredient = ingredientRepository.findById(id);

        //Checking whether the ingredient is available or not
        if(optionIngredient.isPresent())
        {
            Ingredient foundIngredient = optionIngredient.get();
            //If ingredient exist
            return new ResponseEntity<>(foundIngredient, HttpStatus.OK);
        }
        //If ingredient not exist
        return new ResponseEntity<>("Ingredient not found", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<String> updateIngredient(int id, QuantityRequest request) // To update the ingredient
    {
        int quantity = request.getQuantity();
        // Find the ingredient using id
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);
        // Check whether is ingredient is available or not
        if(optionalIngredient.isPresent())
        {
            // Ingredient available means update it
            Ingredient updatedDrink = optionalIngredient.get();
            updatedDrink.setQuantity(quantity);
            ingredientRepository.save(updatedDrink);
            return new ResponseEntity<>("Ingredient updated", HttpStatus.OK);
        }
        //Ingredient not available
        return new ResponseEntity<>("Ingredient not found", HttpStatus.NOT_FOUND);
    }

    public int getIngredientQuantity(String name) // Get ingredient quantity using ingredient name
    {
        Ingredient ingredient = ingredientRepository.findByName(name);
        // Ingredient available or not
        return (ingredient != null) ? ingredient.getQuantity() : 0;
    }

    public void deductIngredientQuantity(String name, int quantityToDeduct) // Deduct the quantity of ingredient
    {
        //Find the ingredient
        Ingredient ingredient = ingredientRepository.findByName(name);
        //Check whether the ingredient is present or not
        if(ingredient != null)
        {
            int currentQuantity = ingredient.getQuantity();
            //Check if the ingredient have sufficient quantity
            if(currentQuantity >= quantityToDeduct)
            {
                //Update the quantity of the ingredient
                ingredient.setQuantity(currentQuantity - quantityToDeduct);
                ingredientRepository.save(ingredient);
            }
            //If the ingredient doesn't have the sufficient quantity
            else
            {
                throw new RuntimeException("Insufficient quantity of " + name + " in inventory");
            }
        }
        //If the ingredient not present
        else
        {
            throw new RuntimeException("Ingredient " + name + "  not found");
        }
    }


}
