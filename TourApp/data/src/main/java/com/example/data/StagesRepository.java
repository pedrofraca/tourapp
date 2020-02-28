package com.example.data;

import androidx.lifecycle.LiveData;

import com.example.data.model.Resource;
import com.example.data.model.Stage;

import java.util.List;

public interface StagesRepository {
     LiveData<Resource<List<Stage>>> getStages();
}
