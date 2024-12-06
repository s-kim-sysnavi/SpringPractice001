package searchman.repository;

import java.util.List;

import searchman.entity.Shain;

public interface ShainRepository {
	//全社員を取得
	List<Shain> findAll();

	//社員IDによる社員取得
	Shain findByShainId(int shainId);

	//社員挿入
	void insertShain(Shain shain);

	//社員更新
	void updateShain(Shain shain);

	//社員削除
	void deleteShain(int shainId);

	void copyShain(Shain shain);

}
