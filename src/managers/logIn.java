package managers;

public interface logIn {
    /**
     * Check if given hashedPassword matches that in the database.
     * @param email email of the given hashedPassword
     * @param hashedPassword hashedPassword to be checked.
     * @return true if given hashedPassword matches that in the database.
     */
    public boolean checkPassword(String email,String hashedPassword);
    /**
     * Check if given email exists in the database.
     * @param email email to be checked.
     * @return true if email exists in the database.
     */

    public boolean checkExistenceEmail(String email);
}
