package Project.CoffeVendingMachine.request;

import jakarta.validation.constraints.Max;

public class QuantityRequest {
    @Max(value = 1000, message = "Maximum quantity allowed is 1000")
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
