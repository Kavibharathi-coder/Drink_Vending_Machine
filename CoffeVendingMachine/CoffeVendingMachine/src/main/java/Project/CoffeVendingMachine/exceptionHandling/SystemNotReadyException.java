package Project.CoffeVendingMachine.exceptionHandling;

public class SystemNotReadyException extends RuntimeException
{
    public SystemNotReadyException()
    {
        super();
    }

    public SystemNotReadyException(String message)
    {
        super(message);
    }
}