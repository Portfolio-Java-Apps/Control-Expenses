package dev.gclopes.ControlExpenses.Model;

public class MovMisExpenseID {
    private long idPersonalGoods;

    private long idMovement;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovMisExpenseID that = (MovMisExpenseID) o;

        if (idPersonalGoods != that.idPersonalGoods) return false;
        return idMovement == that.idMovement;
    }

    @Override
    public int hashCode() {
        int result = (int) (idPersonalGoods ^ (idPersonalGoods >>> 32));
        result = 31 * result + (int) (idMovement ^ (idMovement >>> 32));
        return result;
    }
}
