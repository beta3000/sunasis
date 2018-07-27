package com.sunat.sunasis.batch.version;

import com.sunat.sunasis.model.Version;
import com.sunat.sunasis.repository.IVersionRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Sort;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Component
public class VersionWriter implements ItemWriter<String> {

    @Autowired
    private IVersionRepository versionRepository;

    @Override
    public void write(List<? extends String> list) throws Exception {
        Version version = new Version();
        version.setId(UUID.randomUUID().toString());
        version.setDate(Calendar.getInstance().getTime());
        version.setComplete(false);

        List<Version> lastVersion = versionRepository.findAll(new Sort(Sort.Direction.DESC, "number"));
        if (lastVersion.isEmpty()) {
            version.setNumber(1);
        } else {
            version.setNumber(lastVersion.get(0).getNumber() + 1);
        }
        versionRepository.save(version);
    }
}
