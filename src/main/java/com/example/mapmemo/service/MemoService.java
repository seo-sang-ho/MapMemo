package com.example.mapmemo.service;

import com.example.mapmemo.entity.Memo;
import com.example.mapmemo.repository.MemoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    public List<Memo> getAllMemos(){
        return memoRepository.findAll();
    }

    public Memo save(Memo memo) {
        return memoRepository.save(memo);
    }

    public void deleteMemo(Long id) {
        memoRepository.deleteById(id);
    }
}
