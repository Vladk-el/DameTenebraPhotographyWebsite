@Test(enabled = true)
	public void php() {
		
		String getUrl = "http://localhost/dtpv2/php/services/link/link.php?link=12";
		String urlPost = "http://localhost/dtpv2/php/services/link/link_insert.php";
		
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json; charset=UTF-8");
		
		GetHelper get = new GetHelper();
		String content = get.getAsString(getUrl, headers);
		System.out.println("Content : \n" + content);
				
		PostHelper helper = new PostHelper();
		String response = helper.postAsString(urlPost, content, headers);
		
		System.out.println("Response : " + response);
		
	}