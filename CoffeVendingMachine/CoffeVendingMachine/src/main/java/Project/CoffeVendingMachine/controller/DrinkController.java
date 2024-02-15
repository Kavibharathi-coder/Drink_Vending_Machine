package Project.CoffeVendingMachine.controller;

import Project.CoffeVendingMachine.DrinkInitializer;
import Project.CoffeVendingMachine.entity.Drink;
import Project.CoffeVendingMachine.exceptionHandling.SystemNotReadyException;
import Project.CoffeVendingMachine.request.DrinkRequest;
import Project.CoffeVendingMachine.service.DrinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffeeVending/v1/drink")
@SecurityRequirement(name = "basicAuth")
public class DrinkController
{

    @Autowired
    DrinkService drinkService;

    @Autowired
    DrinkInitializer drinkInitializer;

    private boolean systemReady = false;

    @GetMapping("/initializeSystem")
    @Operation(description = "Initialize system for us")
    public String initializeSystem()
    {
        if (!systemReady)
        {
            //Initialize system only if it's not ready
            drinkInitializer.initializeSystem();
            systemReady = true; //Set the flag to true when initialization is done
            return "System initialized successfully!";
        }
        else
        {
            return "System is already initialized.";
        }
    }

    @GetMapping(path = "/availableDrinks")
    @Operation(description = "Available drinks")
    //To see all the available drinks
    public ResponseEntity<List<Drink>> availableDrinks()
    {
        if (!systemReady)
        {
            throw new SystemNotReadyException("System is not ready. Please initialize the system first.");
        }
       return drinkService.availableDrinks();
    }

    @GetMapping(path = "/availableDrinkAt/{id}")
    @Operation(description = "Available drink at particular id")
    //To see the particular drink available at id
    public ResponseEntity<Object> drinkAvailableAtId(@PathVariable int id)
    {
        if (!systemReady)
        {
            throw new SystemNotReadyException("System is not ready. Please initialize the system first.");
        }
        return drinkService.drinkAvailableAtId(id);
    }


    @PostMapping(path = "/makeCoffee")
    @Operation(description = "Make coffee")
    //To make a coffee
    public ResponseEntity<String> makeCoffee(@Valid @RequestBody DrinkRequest request)
    {
        if (!systemReady)
        {
            throw new SystemNotReadyException("System is not ready. Please initialize the system first.");
        }
        return drinkService.makeCoffee(request.getSize());
    }

     @PostMapping(path = "/makeTea")
     @Operation(description = "Make tea")
     //To make a tea
     public ResponseEntity<String> makeTea(@Valid @RequestBody DrinkRequest request)
     {
         if (!systemReady)
         {
             throw new SystemNotReadyException("System is not ready. Please initialize the system first.");
         }
         return drinkService.makeTea(request.getSize());
     }
    @PutMapping(path = "/updateDrinkDetails/{id}")
    @Operation(description = "Update drinks details at particular id")
    //To update a drink by id
    public ResponseEntity<String> updateDrinkDetails(@PathVariable int id, @Valid @RequestBody Drink drink)
    {
        if (!systemReady)
        {
            throw new SystemNotReadyException("System is not ready. Please initialize the system first.");
        }
        return drinkService.updateDrinkDetails(id, drink);
    }

    @DeleteMapping(path = "/removeDrinkAt/{id}")
    @Operation(description = "Remove drink at particular id")
    //To remove a drink from machine using id
    public ResponseEntity<String> removeDrinkFromMachine(@PathVariable int id)
    {
        if (!systemReady)
        {
            throw new SystemNotReadyException("System is not ready. Please initialize the system first.");
        }
        return drinkService.removeDrinkFromMachine(id);
    }

    @DeleteMapping(path = "/removeAllDrinks")
    @Operation(description = "Remove all the drinks")
    //To remove all the drinks from machine
    public ResponseEntity<String> removeAllDrinksFromMachine()
    {
        if (!systemReady)
        {
            throw new SystemNotReadyException("System is not ready. Please initialize the system first.");
        }
        return drinkService.removeAllDrinksFromMachine();
    }
}
