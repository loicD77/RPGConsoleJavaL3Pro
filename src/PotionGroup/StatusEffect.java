package PotionGroup;

public class StatusEffect {
    private String name;
    private int duration; // Durée de l'effet en tours
    private int power; // Puissance de l'effet
    private String description;

    public StatusEffect(String name, int duration, int power) {
        this.name = name;
        this.duration = duration;
        this.power = power;
        this.description = name + " de puissance " + power + " pendant " + duration + " tours.";
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
