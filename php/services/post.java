	@Test(enabled = false)
	public void phpsave() {

		System.out.println("######## SAVE ########");

		// links
		// String getUrl =
		// "http://localhost/dtpv2/php/services/link/link.php?link=12";
		// String urlPost =
		// "http://localhost/dtpv2/php/services/link/link_insert.php";

		// owners
		// String getUrl =
		// "http://localhost/dtpv2/php/services/owner/owner.php?owner=12";
		// String urlPost =
		// "http://localhost/dtpv2/php/services/owner/owner_insert.php";

		// category
		// String getUrl =
		// "http://localhost/dtpv2/php/services/category/category.php?category=17";
		// String urlPost =
		// "http://localhost/dtpv2/php/services/category/category_insert.php";

		// photo
		String getUrl = "http://localhost/dtpv2/php/services/photo/photo.php?photo=12";
		String urlPost = "http://localhost/dtpv2/php/services/photo/photo_insert.php";

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json; charset=UTF-8");

		GetHelper get = new GetHelper();
		String content = get.getAsString(getUrl, headers);
		System.out.println("Content : \n" + content);

		PostHelper helper = new PostHelper();
		String response = helper.postAsString(urlPost, content, headers);

		System.out.println("Response : " + response);

	}

	@Test(enabled = false)
	public void phpupdate() {

		System.out.println("######## UPDATE ########");

		// links
		// String getUrl =
		// "http://localhost/dtpv2/php/services/link/link.php?link=29";
		// String urlPost =
		// "http://localhost/dtpv2/php/services/link/link_update.php";

		// owners
		// String getUrl =
		// "http://localhost/dtpv2/php/services/owner/owner.php?owner=31";
		// String urlPost =
		// "http://localhost/dtpv2/php/services/owner/owner_update.php";

		// category
		String getUrl = "http://localhost/dtpv2/php/services/category/category.php?category=48";
		String urlPost = "http://localhost/dtpv2/php/services/category/category_update.php";

		// photo
		// String getUrl =
		// "http://localhost/dtpv2/php/services/photo/photo.php?photo=224";
		// String urlPost =
		// "http://localhost/dtpv2/php/services/photo/photo_update.php";

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json; charset=UTF-8");

		GetHelper get = new GetHelper();
		String content = get.getAsString(getUrl, headers);
		System.out.println("Content : \n" + content);

		Scanner sc = new Scanner(System.in);
		sc.nextLine();

		PostHelper helper = new PostHelper();
		String response = helper.postAsString(urlPost, content, headers);

		System.out.println("Response : " + response);

	}

	@Test(enabled = true)
	public void phpremove() {

		System.out.println("######## SAVE ########");

		// links
		// String getUrl =
		// "http://localhost/dtpv2/php/services/link/link.php?link=12";
		// String urlPost =
		// "http://localhost/dtpv2/php/services/link/link_delete.php";

		// owners
		// String getUrl =
		// "http://localhost/dtpv2/php/services/owner/owner.php?owner=12";
		// String urlPost =
		// "http://localhost/dtpv2/php/services/owner/owner_delete.php";

		// category
		// String getUrl =
		// "http://localhost/dtpv2/php/services/category/category.php?category=17";
		// String urlPost =
		// "http://localhost/dtpv2/php/services/category/category_delete.php";

		// photo
		String getUrl = "http://localhost/dtpv2/php/services/photo/photo.php?photo=12";
		String urlPost = "http://localhost/dtpv2/php/services/photo/photo_delete.php";

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json; charset=UTF-8");

		GetHelper get = new GetHelper();
		String content = get.getAsString(getUrl, headers);
		System.out.println("Content : \n" + content);

		PostHelper helper = new PostHelper();
		String response = helper.postAsString(urlPost, content, headers);

		System.out.println("Response : " + response);

	}