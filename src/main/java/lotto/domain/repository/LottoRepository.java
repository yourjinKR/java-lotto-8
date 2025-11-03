package lotto.domain.repository;


import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;

public class LottoRepository {
    private static final List<Lotto> lottoList = new ArrayList<>();

    public void save(Lotto lotto) {
        lottoList.add(lotto);
    }

    public void saveAll(List<Lotto> lottoList) {
        lottoList.forEach(this::save);
    }

    public List<Lotto> findAll() {
        return new ArrayList<>(lottoList);
    }

    public void clear() {
        lottoList.clear();
    }
}
