package searchman.entity;

public class Shain {
	//社員属性(プロパティ)

	private int id;
	private String name;
	private String sei;
	private int nen;
	private String address;
	private String email;
	private Long userId;
	private String profileImage;

	public record ShainAccount(String email, String password) {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSei() {
		return sei;
	}

	public void setSei(String sei) {
		this.sei = sei;
	}

	public int getNen() {
		return nen;
	}

	public void setNen(int nen) {
		this.nen = nen;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	//	public String getEmail() {
	//		return email;
	//	}
	//
	//	public void setEmail(String email) {
	//		this.email = email;
	//	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
