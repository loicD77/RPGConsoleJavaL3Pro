package PotionGroup;

public class StatusEffect {
    private String name;
    private int duration; // Durée de l'effet en tours
    private int power; // Ajout du troisième paramètre
    private String description;

    public StatusEffect(String name, int duration, int power) {
        this.name = name;
        this.duration = duration;
        this.power = power;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public void decreaseDuration() {
        duration--; // Diminue la durée de l'effet
    }

    public int getPower() {
        return power; // Retourne la puissance de l'effet
    }
}
