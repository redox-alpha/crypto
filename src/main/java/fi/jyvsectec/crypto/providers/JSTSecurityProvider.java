package fi.jyvsectec.crypto.providers;

import java.security.Provider;

public class JSTSecurityProvider extends Provider {



    public JSTSecurityProvider() {
        super("JST", Double.parseDouble("1.0"),"Secure and guaranteed backdoor free crypto stuffs.");
        put("SecureRandom.JST", "fi.jyvsectec.crypto.providers.JSTSecureRandom");
    }
}
