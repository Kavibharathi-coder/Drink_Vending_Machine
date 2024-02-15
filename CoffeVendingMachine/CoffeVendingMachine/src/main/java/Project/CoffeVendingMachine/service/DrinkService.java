package Project.CoffeVendingMachine.service;

import Project.CoffeVendingMachine.entity.Drink;
import Project.CoffeVendingMachine.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DrinkService
{
    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    IngredientService ingredientService;


    public ResponseEntity<List<Drink>> availableDrinks()  // To see all the drinks
    {
        //Find all the drinks form repository
        List<Drink> drinks = drinkRepository.findAll();
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }


    public ResponseEntity<Object> drinkAvailableAtId(int id) //To see drink available at particular id
    {
        //find the drink by id
        Optional<Drink> optionalDrink = drinkRepository.findById(id);

        //Checking whether the drink is available or not
        if(optionalDrink.isPresent())
        {
            Drink foundDrink = optionalDrink.get();
            //If drink exist
            return new ResponseEntity<>(foundDrink, HttpStatus.OK);
        }
        //If drink not exist
        return new ResponseEntity<>("Drink not found", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<String> makeCoffee(String size)  // To make a coffee
    {
        //check whether the coffee is available in the drinks are not
        List<Drink> drinks = drinkRepository.findAll();
        int flag = 0;

        for(Drink drink : drinks)
        {
            if (drink.getName().equals("Coffee")) {
                flag = 1;
                break;
            }
        }

        if(flag == 0) return new ResponseEntity<>("Coffee is not available.", HttpStatus.BAD_REQUEST);

        //Check for the correct size
        if(size.equals("small") || size.equals("regular") || size.equals("large"))
        {
            int coffeeBeansNeeded = 0;
            int milkNeeded = 0;
            int sugarNeeded = 0;

            switch (size) {
                case "small":
                    coffeeBeansNeeded = 1;
                    milkNeeded = 1;
                    sugarNeeded = 1;
                    break;
                case "regular":
                    coffeeBeansNeeded = 2;
                    milkNeeded = 2;
                    sugarNeeded = 2;
                    break;
                case "large":
                    coffeeBeansNeeded = 3;
                    milkNeeded = 3;
                    sugarNeeded = 3;
                    break;
            }

            // Get available ingredients from ingredient service
            int availableCoffeeBeans = ingredientService.getIngredientQuantity("coffeeBeans");
            int availableMilk = ingredientService.getIngredientQuantity("milk");
            int availableSugar = ingredientService.getIngredientQuantity("sugar");

            // If Ingredients are insufficient to make a coffee
            if (availableCoffeeBeans < coffeeBeansNeeded || availableMilk < milkNeeded || availableSugar < sugarNeeded)
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient ingredients to make coffee");
            }
            else
            {
                // Deduct the quantity from the ingredient after making coffee
                ingredientService.deductIngredientQuantity("coffeeBeans", coffeeBeansNeeded);
                ingredientService.deductIngredientQuantity("milk", milkNeeded);
                ingredientService.deductIngredientQuantity("sugar", sugarNeeded);
                return ResponseEntity.ok("Coffee is ready! Enjoy your drink.");
            }
        }
        else
        {
            return ResponseEntity.badRequest().body("Invalid size provided.");
        }
    }


    public ResponseEntity<String> makeTea(String size)  // To make a tea
    {
        //check whether the coffee is available in the drinks are not
        List<Drink> drinks = drinkRepository.findAll();
        int flag = 0;

        for(Drink drink : drinks)
        {
            if (drink.getName().equals("Tea")) {
                flag = 1;
                break;
            }
        }
        if(flag == 0) return new ResponseEntity<>("Tea is not available.", HttpStatus.BAD_REQUEST);

        //Check for the correct size
        if(size.equals("small") || size.equals("regular") || size.equals("large"))
        {
            int teaLeavesNeeded = 0;
            int milkNeeded = 0;
            int sugarNeeded = 0;

            //setting the amount of ingredient
            switch (size) {
                case "small":
                    teaLeavesNeeded = 1;
                    milkNeeded = 1;
                    sugarNeeded = 1;
                    break;
                case "regular":
                    teaLeavesNeeded = 2;
                    milkNeeded = 2;
                    sugarNeeded = 2;
                    break;
                case "large":
                    teaLeavesNeeded = 3;
                    milkNeeded = 3;
                    sugarNeeded = 3;
                    break;
            }

            // Get available ingredients from ingredient service
            int availableCoffeeBeans = ingredientService.getIngredientQuantity("teaLeaves");
            int availableMilk = ingredientService.getIngredientQuantity("milk");
            int availableSugar = ingredientService.getIngredientQuantity("sugar");

            // If Ingredients are insufficient to make a coffee
            if (availableCoffeeBeans < teaLeavesNeeded || availableMilk < milkNeeded || availableSugar < sugarNeeded)
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient ingredients to make tea");
            }
            else
            {
                // Deduct the quantity from the ingredient after making coffee
                ingredientService.deductIngredientQuantity("teaLeaves", teaLeavesNeeded);
                ingredientService.deductIngredientQuantity("milk", milkNeeded);
                ingredientService.deductIngredientQuantity("sugar", sugarNeeded);
                return ResponseEntity.ok("Tea is ready! Enjoy your drink.");
            }
        }
        else
        {
            return ResponseEntity.badRequest().body("Invalid size provided.");
        }
    }


    public ResponseEntity<String> updateDrinkDetails(int id, Drink drink)  // To update the drink details
    {
        //find the drink by id
        Optional<Drink> optionalDrink = drinkRepository.findById(id);
        //Check whether the drink is available or not
        if(optionalDrink.isPresent())
        {
            //If available means update it
            Drink updatedDrink = optionalDrink.get();
            updatedDrink.setName(drink.getName());
            return new ResponseEntity<>("Drink updated", HttpStatus.OK);
        }
        //If drink not available
        return new ResponseEntity<>("Drink not found", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<String> removeDrinkFromMachine(int id) // To remove the drink from machine using drink id
    {
        //Find the drink by id
        Optional<Drink> optionalDrink = drinkRepository.findById(id);
        //check whether the drink is available or not
        if(optionalDrink.isPresent())
        {
            //If the drink available means delete it
            drinkRepository.delete(optionalDrink.get());
            return new ResponseEntity<>("Drink deleted", HttpStatus.OK);
        }
        //If the drink not available
        return new ResponseEntity<>("Drink not found", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<String> removeAllDrinksFromMachine() // To remove all the drinks from the machine
    {
        //Remove all the drink in repository
        drinkRepository.deleteAll();
        return new ResponseEntity<>("All drinks are deleted", HttpStatus.OK);
    }
}
