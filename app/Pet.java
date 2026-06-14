public class Pet{
    private int hunger;
    private int happiness;


    public pet(){
        this.hunger=50;
        this.happiness=50;
    }
    public void eat(){
        if(this.hunger < 100){
            this.hunger += 10;
        }
    }

    public int getHunger() {
        return hunger;
    }
}
