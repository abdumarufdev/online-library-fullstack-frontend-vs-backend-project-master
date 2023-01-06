package com.example.projectlibrary.Service;

import com.example.projectlibrary.Entayti.QorzinkaIjara;
import com.example.projectlibrary.Entayti.Rents;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Repozitory.QorzinkaIjaraRepository;
import com.example.projectlibrary.Repozitory.RentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RentsServicelmpl implements RentsService{
    @Autowired
    RentsRepository rentsRepository;

    @Autowired
    QorzinkaIjaraRepository qorzinkaIjaraRepository;

    @Override
    public ApiResponse saveIjara(Integer kitobId, Integer userId, String mudat, String kitobNomi, String kitobMualifi, String ismvsfam, String seriya, String telefon) {
        if (!kitobId.equals(0) && !userId.equals(0) && !mudat.equals("")){
            Rents rents = new Rents();
            rents.setMudat(mudat);
            rents.setKitobId(kitobId);
            rents.setUserId(userId);
            rents.setBerildi(false);
            rents.setOlindi(false);
            rents.setBerilganVaqt("");
            rents.setOlinganVaqt("");
            rents.setIsmvsfamilya(ismvsfam);
            rents.setSeriya(seriya);
            rents.setTelefon(telefon);
            rents.setKitobNomi(kitobNomi);
            rents.setKitobMualifi(kitobMualifi);
            rentsRepository.save(rents);
            return new ApiResponse("success",true);
        }
        return new ApiResponse("warning",false);
    }

    @Override
    public ApiResponse editMudats(String mudatEdit, Integer idEdit) {
        Optional<Rents> optionalRents = rentsRepository.findById(idEdit);
        if (!optionalRents.isPresent()) return new ApiResponse("xatolik",false);

        Rents r = optionalRents.get();

        String[] b = mudatEdit.split(",");
        String[] a = b[0].split(" ");
        String kuni = a[0];
        String oyi = a[1];

        String newData = String.valueOf(LocalDate.now());

        LocalDate date = LocalDate.parse(newData);

        LocalDate kun = null;

        if (oyi.equals("kun")){

           kun = date.plusDays(Long.parseLong(kuni));
            return new ApiResponse("ssss",kun+","+newData+","+r.getBerildi()+","+r.getOlindi());

        }
        if (oyi.equals("hafta")){

            kun = date.plusDays(Long.parseLong(kuni)*7);
            return new ApiResponse("ssss",kun+","+newData+","+r.getBerildi()+","+r.getOlindi());

        }
        if (oyi.equals("oy")){

            kun = date.plusDays(Long.parseLong(kuni)*30);
            return new ApiResponse("ssss",kun+","+newData+","+r.getBerildi()+","+r.getOlindi());

        }

        return new ApiResponse("ssss",kun+","+newData+","+r.getBerildi()+","+r.getOlindi());
    }

    @Override
    public ApiResponse saveIjaraFull(String berilganSana, String qaytarilganSana, Integer editId) {
        Optional<Rents> optionalRents = rentsRepository.findById(editId);
        if (!optionalRents.isPresent()) return new ApiResponse("error",false);

            Rents rents = optionalRents.get();
            rents.setKitobMualifi(rents.getKitobMualifi());
            rents.setBerildi(true);
            rents.setOlindi(false);
            rents.setUserId(rents.getUserId());
            rents.setBerilganVaqt(berilganSana);
            rents.setOlinganVaqt(qaytarilganSana);
            rents.setMudat(rents.getMudat());
            rents.setIsmvsfamilya(rents.getIsmvsfamilya());
            rents.setSeriya(rents.getSeriya());
            rents.setKitobNomi(rents.getKitobNomi());
            rents.setTelefon(rents.getTelefon());
            rents.setKitobId(rents.getKitobId());
            rentsRepository.save(rents);
            return new ApiResponse("success",true);
    }

    @Override
    public ApiResponse berIjaraga(Integer id, String qaytarilganSana, String berilganSana) {
        Optional<Rents> optionalRents = rentsRepository.findById(id);
        if (!optionalRents.isPresent()) return new ApiResponse("error",false);

        Rents rents = optionalRents.get();
        rents.setKitobMualifi(rents.getKitobMualifi());
        rents.setBerildi(true);
        rents.setOlindi(false);
        rents.setUserId(rents.getUserId());
        rents.setBerilganVaqt(berilganSana);
        rents.setOlinganVaqt(qaytarilganSana);
        rents.setMudat(rents.getMudat());
        rents.setIsmvsfamilya(rents.getIsmvsfamilya());
        rents.setSeriya(rents.getSeriya());
        rents.setKitobNomi(rents.getKitobNomi());
        rents.setTelefon(rents.getTelefon());
        rents.setKitobId(rents.getKitobId());
        rentsRepository.save(rents);
        return new ApiResponse("success",true);
    }

    @Override
    public ApiResponse qaytiIjara(Integer id) {
        Optional<Rents> optionalRents = rentsRepository.findById(id);
        if (!optionalRents.isPresent()) return new ApiResponse("error",false);

        Rents rents = optionalRents.get();
        rents.setKitobMualifi(rents.getKitobMualifi());
        rents.setBerildi(true);
        rents.setOlindi(true);
        rents.setUserId(rents.getUserId());
        rents.setBerilganVaqt(rents.getBerilganVaqt());
        rents.setOlinganVaqt(rents.getOlinganVaqt());
        rents.setMudat(rents.getMudat());
        rents.setIsmvsfamilya(rents.getIsmvsfamilya());
        rents.setSeriya(rents.getSeriya());
        rents.setKitobNomi(rents.getKitobNomi());
        rents.setTelefon(rents.getTelefon());
        rents.setKitobId(rents.getKitobId());
        rentsRepository.save(rents);
        return new ApiResponse("success",true);
    }

    @Override
    public ApiResponse trueAndfalse(Integer id) {
        Optional<Rents> optionalRents = rentsRepository.findById(id);
        if (!optionalRents.isPresent()) return new ApiResponse("false",false);
        Rents rents = optionalRents.get();
        String full = rents.getBerildi()+","+rents.getOlindi();
        return new ApiResponse("true",full);
    }

    @Override
    public ApiResponse Select(Integer id) {
        Optional<Rents> optionalRents = rentsRepository.findById(id);
        if (!optionalRents.isPresent()) return new ApiResponse("false",false);
        Rents rents = optionalRents.get();
        String full = rents.getBerildi()+","+rents.getOlindi();
        return new ApiResponse("true",full);
    }

    @Override
    public ApiResponse berOl(Integer id) {
        Optional<Rents> optionalRents = rentsRepository.findById(id);
        if (!optionalRents.isPresent()) return new ApiResponse("error",false);

        Rents rents = optionalRents.get();
        rents.setKitobMualifi(rents.getKitobMualifi());
        rents.setBerildi(true);
        rents.setOlindi(true);
        rents.setUserId(rents.getUserId());
        rents.setBerilganVaqt(rents.getBerilganVaqt());
        rents.setOlinganVaqt(rents.getOlinganVaqt());
        rents.setMudat(rents.getMudat());
        rents.setIsmvsfamilya(rents.getIsmvsfamilya());
        rents.setSeriya(rents.getSeriya());
        rents.setKitobNomi(rents.getKitobNomi());
        rents.setTelefon(rents.getTelefon());
        rents.setKitobId(rents.getKitobId());
        rentsRepository.save(rents);

        QorzinkaIjara qorzinkaIjara = new QorzinkaIjara();
        qorzinkaIjara.setBerildi(true);
        qorzinkaIjara.setKitobMualifi(rents.getKitobMualifi());
        qorzinkaIjara.setOlindi(true);
        qorzinkaIjara.setUserId(rents.getUserId());
        qorzinkaIjara.setBerilganVaqt(rents.getBerilganVaqt());
        qorzinkaIjara.setOlinganVaqt(rents.getOlinganVaqt());
        qorzinkaIjara.setMudat(rents.getMudat());
        qorzinkaIjara.setIsmvsfamilya(rents.getIsmvsfamilya());
        qorzinkaIjara.setSeriya(rents.getSeriya());
        qorzinkaIjara.setKitobNomi(rents.getKitobNomi());
        qorzinkaIjara.setTelefon(rents.getTelefon());
        qorzinkaIjara.setKitobId(rents.getKitobId());
        qorzinkaIjaraRepository.save(qorzinkaIjara);
        rentsRepository.deleteById(id);
        return new ApiResponse("success",true);
    }
}
