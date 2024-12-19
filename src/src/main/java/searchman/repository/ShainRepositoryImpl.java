package searchman.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import searchman.entity.Shain;

@Repository
public class ShainRepositoryImpl implements ShainRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Shain> findAll() {
		//SQL文の作成
		final String sql = "select id, name, sei, nen, address,user_id from shain order by user_id";

		//SQLの実行
		List<Shain> shainList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Shain>(Shain.class));
		//		System.out.println(shainList.get(6).getUserId());

		return shainList;
	}

	@Override
	public Shain findByShainId(Long shainId) {
		//SQL文の作成
		final String sql = "select id, name, sei, nen, address ,user_id  from shain where user_id = :user_id";

		// パラメータの作成
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", shainId);

		// SQLの実行
		List<Shain> shainList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Shain>(Shain.class));

		//リストを判定して戻す
		return shainList.isEmpty() ? null : shainList.get(0);
	}

	@Override
	public void insertShain(Shain shain) {
		//SQL文の作成
		final String sql = "insert into shain(name, sei, nen, address ,user_id) "
				+ "values(:name,:sei,:nen,:address ,:user_id)";

		// パラメータの作成
		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("name", shain.getName());
		param.addValue("sei", shain.getSei());
		param.addValue("nen", shain.getNen());
		param.addValue("address", shain.getAddress());

		param.addValue("user_id", shain.getUserId());

		System.out.println(sql);
		System.out.println(222);

		// SQLの実行
		jdbcTemplate.update(sql, param);

	}

	public void insertUserId(Shain shain) {
		//SQL文の作成
		final String sql = "update shain set user_id=:user_id "
				+ "where name=:name and sei=:sei and nen=:nen and address=:address";

		// パラメータの作成
		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("name", shain.getName());
		param.addValue("sei", shain.getSei());
		param.addValue("nen", shain.getNen());
		param.addValue("address", shain.getAddress());

		param.addValue("user_id", shain.getUserId());

		System.out.println(sql);
		System.out.println(222);

		// SQLの実行
		jdbcTemplate.update(sql, param);

	}

	@Override
	public void updateShain(Shain shain) {
		//SQL文の作成
		final String sql = "update shain set name=:name, sei=:sei, nen=:nen, address=:address "
				+ "where user_id=:user_id";

		// パラメータの作成
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", shain.getId());
		param.addValue("name", shain.getName());
		param.addValue("sei", shain.getSei());
		param.addValue("nen", shain.getNen());
		param.addValue("address", shain.getAddress());
		param.addValue("user_id", shain.getUserId());

		// SQLの実行
		jdbcTemplate.update(sql, param);

	}

	//	@Override
	//	public void pathsave(Shain shain) {
	//		//SQL文の作成
	//		final String sql = "update shain set profile_image=:profile_image "
	//				+ "where user_id=:user_id";
	//
	//		// パラメータの作成
	//		MapSqlParameterSource param = new MapSqlParameterSource();
	//		param.addValue("profile_image", shain.getProfileImage());
	//		param.addValue("user_id", shain.getUserId());
	//
	//		// SQLの実行
	//		jdbcTemplate.update(sql, param);
	//
	//	}

	@Override
	public void deleteShain(Long shainId) {
		//SQL文の作成
		final String sql = "delete  from shain where user_id=:user_id";

		// パラメータの作成
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", shainId);

		// SQLの実行
		jdbcTemplate.update(sql, param);

	}

	//	@Override
	//	public void copyShain(Shain shain) {
	//		//SQL文の作成
	//		final String sql = "insert into shain(name, sei, nen, address) "
	//				+ "values(:name,:sei,:nen,:address)";
	//
	//		// パラメータの作成
	//		MapSqlParameterSource param = new MapSqlParameterSource();
	//
	//		param.addValue("name", shain.getName());
	//		param.addValue("sei", shain.getSei());
	//		param.addValue("nen", shain.getNen());
	//		param.addValue("address", shain.getAddress());
	//
	//		// SQLの実行
	//		jdbcTemplate.update(sql, param);
	//
	//	}

}
