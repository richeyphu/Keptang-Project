package Account;

import java.io.IOException;

public interface RegisterOption {

	// public abstract boolean isValidPassword();
	// public abstract boolean isPasswordMatched();
	public abstract boolean isFieldBlank();
	public abstract boolean isContainNG();
	public abstract boolean isIdDuplicated();

	public abstract void create() throws IOException;
}
