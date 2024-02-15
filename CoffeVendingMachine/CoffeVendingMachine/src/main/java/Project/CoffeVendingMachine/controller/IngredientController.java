package Project.CoffeVendingMachine.controller;

import Project.CoffeVendingMachine.entity.Ingredient;
import Project.CoffeVendingMachine.request.QuantityRequest;
import Project.CoffeVendingMachine.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffeeVending/v1/ingredient")
@SecurityRequirement(name = "basicAuth")

public class IngredientController
{
    @Autowired
    IngredientService ingredientService;
    @GetMapping(path = "/availableIngredients")
    @Operation(description = "Available ingredients")
    //To see all the available ingredients
    public ResponseEntity<List<Ingredient>> availableIngredients()
    {
        return ingredientService.availableIngredients();
    }

    @GetMapping(path = "/availableIngredientAt/{id}")
    @Operation(description = "Available ingredient at particular id")
    //To see the particular ingredient at id
    public ResponseEntity<Object> availableIngredientAt(@PathVariable int id)
    {
        return ingredientService.availableIngredientAt(id);
    }


    @PutMapping(path = "/updateIngredient/{id}")
    @Operation(description = "Update ingredient at particular id")
    //To update an ingredient
    public ResponseEntity<String> updateIngredient(@PathVariable int id, @Valid @RequestBody QuantityRequest request)
    {
        return ingredientService.updateIngredient(id, request);
    }
}
