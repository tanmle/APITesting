package test.restful.TemplateClasses;

public class Authentication {
	public enum AuthenType {
		BASIC, DIGEST
	}

	private String _sUsername;
	private String _sPassword;
	private AuthenType _eType;

	public Authentication(){}
	
	public Authentication(String Username, String Password, AuthenType Type)
	{
		_sUsername = Username;
		_sPassword = Password;
		_eType = Type;
	}
	
	public String getUsername() {
		return _sUsername;
	}

	public void setUsername(String sUsername) {
		this._sUsername = sUsername;
	}

	public String getPassword() {
		return _sPassword;
	}

	public void setPassword(String sPassword) {
		this._sPassword = sPassword;
	}

	public AuthenType getType() {
		return _eType;
	}

	public void setType(AuthenType eType) {
		this._eType = eType;
	}
}
