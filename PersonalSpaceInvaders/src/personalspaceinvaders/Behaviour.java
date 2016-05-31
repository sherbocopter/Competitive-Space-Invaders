package personalspaceinvaders;

/**
 *
 * @author SHerbocopter
 */
public abstract class Behaviour
{

    protected Part part;
    private boolean isActive = true;

    /**
     *
     * Behaviour class is only used to control an entity via the ControllerPart
     * which server as a collection of behaviours.
     *
     * This is the reason why there is no draw function.
     *
     */
    public final boolean isActive()
    {
        return isActive;
    }

    public final void setActive(boolean isActive)
    {
        this.isActive = isActive;
    }

    public final Part getPart()
    {
        return part;
    }

    public final void setPart(Part part)
    {
        this.part = part;
    }

    public void initialize()
    {

    }

    public void cleanup()
    {

    }

    public void update(float delta)
    {

    }
}
