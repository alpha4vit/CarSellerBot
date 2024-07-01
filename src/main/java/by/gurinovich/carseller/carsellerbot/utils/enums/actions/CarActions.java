package by.gurinovich.carseller.carsellerbot.utils.enums.actions;

public enum CarActions {
    CAR_BRAND_NEXT_BUTTON,
    CAR_BRAND_PREVIOUS_BUTTON,
    CAR_MODEL_NEXT_BUTTON,
    CAR_MODEL_PREVIOUS_BUTTON,
    CAR_GENERATION_NEXT_BUTTON,
    CAR_GENERATION_PREVIOUS_BUTTON,
    CAR_LOT_NEXT_BUTTON,
    CAR_LOT_PREVIOUS_BUTTON;

    public static CarActions fromString(String action) {
        try{
            return CarActions.valueOf(action);
        }
        catch (Exception e){
            return null;
        }
    }

}
