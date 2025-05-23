package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {
    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameName(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameName(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .build();
        assertTrue(ALICE.isSameName(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameName(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameName(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameName(editedBob));
    }

    @Test
    public void isSameTelegram() {
        // same object -> returns true
        assertTrue(ALICE.isSameTelegram(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameTelegram(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE)
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .build();
        assertTrue(ALICE.isSameTelegram(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withTelegram(VALID_TELEGRAM_BOB).build();
        assertFalse(ALICE.isSameTelegram(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new PersonBuilder(BOB).withTelegram(VALID_TELEGRAM_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameTelegram(editedBob));

        // name has trailing spaces, all other attributes same -> throws illegal argument exception
        String telegramWithTrailingSpaces = VALID_TELEGRAM_BOB + " ";
        assertThrows(IllegalArgumentException.class, Telegram.MESSAGE_CONSTRAINTS, () -> new PersonBuilder(BOB)
                .withTelegram(telegramWithTrailingSpaces).build());
    }

    @Test
    public void toggleFav() {
        Person notFavourite = new PersonBuilder(ALICE).build();
        Person favourite = new PersonBuilder(ALICE).withFavourite(true).build();
        assertTrue(notFavourite.toggleFav().isSameName(favourite));
        assertTrue(favourite.toggleFav().isSameName(notFavourite));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", telegram=" + ALICE.getTelegram().get()
                + ", modules=" + ALICE.getModules()
                + ", isFavourite=" + ALICE.getIsFavourite()
                + "}";

        assertEquals(expected, ALICE.toString());
    }
}
