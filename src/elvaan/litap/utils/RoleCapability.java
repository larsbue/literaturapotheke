package elvaan.litap.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleCapability {
    /*
     * Prüfe role auf berechtigungen
     */
	public static boolean has(String role, String capability) {
		Map<String, List<String>> caps = new HashMap<String, List<String>>();
		caps.put("guest", Arrays.asList("read-literature", "use-search"));
		caps.put("user",  Arrays.asList("read-literature", "use-search", "rate+comment-literature", "post-literature"));
		caps.put("admin", Arrays.asList("read-literature", "use-search", "rate+comment-literature", "post-literature", "lock+delete-literature+user", "view+edit-user"));
		
		List<String> role_caps = caps.get(role);
		if(role_caps != null)
			return role_caps.contains(capability);
		return false;
	}
}
