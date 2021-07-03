package com.example.helpersDemo;


import com.example.helpersDemo.pojo_models.user.UpdateUserBody;
import com.example.helpersDemo.pojo_models.user.User;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Response;

import java.io.IOException;

@SpringBootTest
class HelpersDemoApplicationTests implements WithAssertions {

	@Test
	void getUserTest() throws IOException {
		String email = "janet.weaver@reqres.in";
		String lastName = "Weaver";
		String url = "https://reqres.in/#support-heading";

		Response<User> response;

		APIInterface client = APIClientHelper.getClient().create(APIInterface.class);

		response = client.getUser().execute();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(response.isSuccessful()).isTrue();
			softly.assertThat(response.body().getData().getEmail()).as("email").isEqualTo(email);
			softly.assertThat(response.body().getData().getLastName()).as("last name").isEqualTo(lastName);
			softly.assertThat(response.body().getSupport().getUrl()).as("url").isEqualTo(url);
		});
	}

	@Test
	void updateUserTest() throws IOException {
		String name = "morpheus";
		String job = "zion resident";

		Response<UpdateUserBody> response;

		APIInterface client = APIClientHelper.getClient().create(APIInterface.class);

		response = client.updateUser(name,job).execute();

		System.out.println(response.code()+ " ---- Code ");
		System.out.println(response.body()+ " ---- Body ");

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(response.isSuccessful()).isTrue();
			softly.assertThat(response.body().getJob()).as("job").isEqualTo(job);
			softly.assertThat(response.body().getName()).as("name").isEqualTo(name);
		});
	}

	@Test
	void updateUserWithPatchTest() throws IOException {
		String name = "morpheus";
		String job = "zion resident";

		Response<UpdateUserBody> response;

		APIInterface client = APIClientHelper.getClient().create(APIInterface.class);

		response = client.updateUser(name,job).execute();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(response.code()).as("code").isEqualTo(201);
			softly.assertThat(response.body().getJob()).as("job").isEqualTo(job);
			softly.assertThat(response.body().getName()).as("name").isEqualTo(name);
		});
	}

	@Test
	void deleteUserTest() throws IOException {
		Response<Void> response;

		APIInterface client = APIClientHelper.getClient().create(APIInterface.class);

		response = client.deleteUser("2").execute();

		assertThat(response.code()).isEqualTo(204);
	}
}
