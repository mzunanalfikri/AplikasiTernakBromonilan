package com.rfid.ternakbromonilan.controller;

import com.rfid.ternakbromonilan.dto.SapiDto;
import com.rfid.ternakbromonilan.model.Penyakit;
import com.rfid.ternakbromonilan.model.Sapi;
import com.rfid.ternakbromonilan.model.Treatment;
import com.rfid.ternakbromonilan.repository.PenyakitRepository;
import com.rfid.ternakbromonilan.repository.SapiRepository;
import com.rfid.ternakbromonilan.repository.TreatmentRepository;
import com.rfid.ternakbromonilan.service.SapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private SapiService sapiService;
    @Autowired
    private TreatmentRepository treatmentRepository;
    @Autowired
    private PenyakitRepository penyakitRepository;

    @Autowired
    private SapiRepository sapiRepository;

    private static Integer chipid = 0;

    @GetMapping("/setchipid")
    public String setChipId(@RequestParam int id){
        System.out.println(chipid);
        chipid = id;
        return "null";
    }

    @GetMapping("/")
    public String home(Principal principal){
        System.out.println(chipid);
        if (principal == null){
            return "root";
        }
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/sapi-card")
    public String showDataSapi(Principal principal, Model model){
        List<Sapi> dataSapi = sapiService.getAllSapi();
        List<List<Sapi>> sapiShow = new ArrayList<>();
        List<Sapi> temp ;
        for (int i = 0; i < dataSapi.size() ; i+=3) {
            temp = new ArrayList<>();
            if (i < dataSapi.size()) temp.add(dataSapi.get(i));
            if (i+1 < dataSapi.size()) temp.add(dataSapi.get(i+1));
            if (i+2 < dataSapi.size()) temp.add(dataSapi.get(i+2));
            sapiShow.add(temp);
        }
        model.addAttribute("dataSapi", sapiShow);
        model.addAttribute("user", principal);
//        model.addAttribute("filter", new Filter());
        return "all_data_sapi";
    }

    @GetMapping("/sapi")
    public String showDataSapiCard(Model model, @RequestParam(defaultValue = "0") int page){
        model.addAttribute("data", sapiService.getSapiPagination(page));
        model.addAttribute("currentPage", page);
        model.addAttribute("chip", chipid);
        return "all_data_sapi_pagination";
    }

    @GetMapping("/sapi/add")
    public String addDataSapi(Model model){
        SapiDto sapiDto = new SapiDto();
        if (chipid != 0){
            sapiDto.setNomorSapi(chipid.longValue());
        }
        model.addAttribute("sapi", sapiDto);
        return "add_data_sapi";
    }

    @GetMapping("/sapi/{id}")
    public String detailDataSapi(@PathVariable("id") Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("sapi", sapiService.getSapiById(id));
        model.addAttribute("list_penyakit", penyakitRepository.findByIdsapi(id));
        model.addAttribute("list_treatment", treatmentRepository.findByIdsapi(id));
        sapiService.getSapiById(id).getTanggalBirahi().forEach(System.out::println);
        System.out.println(sapiService.getSapiById(id));
        return "detail_data_sapi";
    }

    @GetMapping("/sapi/delete/{id}")
    public String deleteSapiById(@PathVariable("id") Long id){
        sapiRepository.delete(sapiRepository.findById(id).get());
        return "redirect:/sapi?deletesuccess";
    }

    @PostMapping("/search")
    public String searchId(@RequestParam("nomorsapi") Long nomorSapi, Model model){
        model.addAttribute("data", new PageImpl<>(sapiRepository.findAllByNomorSapi(nomorSapi)));
        model.addAttribute("currentPage", 1);
        model.addAttribute("chip", chipid);
        return "all_data_sapi_pagination";
    }

    @GetMapping("/searchbychip")
    public String searchByChip( Model model){
        model.addAttribute("data", new PageImpl<>(sapiRepository.findAllByNomorSapi(chipid.longValue())));
        model.addAttribute("currentPage", 1);
        chipid = 0;
        model.addAttribute("chip", chipid);
        return "all_data_sapi_pagination";
    }

    @PostMapping("/sapi/add")
    public String addDataSapiPost(@ModelAttribute("sapi") @Validated SapiDto sapiDto,
                                  BindingResult result, Principal principal) throws Exception {
        sapiDto.setTanggalTulis(new Date(System.currentTimeMillis()));
        System.out.println((new Date(System.currentTimeMillis())).toString());
        System.out.println(sapiDto);
        sapiService.save(sapiDto);
        return "redirect:/sapi?success";
    }

    @GetMapping("/penyakit/delete")
    public String deletePenyakitById(@RequestParam Long id, Long idsapi){
        penyakitRepository.delete(penyakitRepository.findById(id).get());
        return "redirect:/sapi/" + idsapi.toString();
    }

    @PostMapping("/penyakit/add/{idsapi}")
    public String addPenyakit(@PathVariable Long idsapi, @RequestParam("jenispenyakit") String jenisPenyakit, @RequestParam("tanggalpenyakit") String tanggalPenyakit){
        Penyakit penyakit = new Penyakit();
        penyakit.setIdsapi(idsapi);
        penyakit.setJenis(jenisPenyakit);
        penyakit.setTanggal(Date.valueOf(tanggalPenyakit));
        penyakitRepository.save(penyakit);
        return "redirect:/sapi/" + idsapi.toString();
    }

    @GetMapping("/treatment/delete")
    public String deleteTreatmentById(@RequestParam Long id, Long idsapi){
        treatmentRepository.delete(treatmentRepository.findById(id).get());
        return "redirect:/sapi/" + idsapi.toString();
    }

    @PostMapping("/treatment/add/{idsapi}")
    public String addTreatment(@PathVariable Long idsapi, @RequestParam("jenistreatment") String jenisTreatment, @RequestParam("tanggaltreatment") String tanggalTreatment){
        Treatment treatment = new Treatment();
        treatment.setIdsapi(idsapi);
        treatment.setJenis(jenisTreatment);
        treatment.setTanggal(Date.valueOf(tanggalTreatment));
        treatmentRepository.save(treatment);
        return "redirect:/sapi/" + idsapi.toString();
    }

    // Tanggal Birahi endpoint (delete + add)
    @GetMapping("/tanggalbirahi/delete")
    public String deleteTanggalBirahi(@RequestParam Long id, String tanggalBirahi){
        Sapi sapi = sapiRepository.findById(id).get();
        List<Date> tanggal = sapi.getTanggalBirahi();
        tanggal.remove(Date.valueOf(tanggalBirahi));
        sapi.setTanggalBirahi(tanggal);
        sapiRepository.save(sapi);
        return "redirect:/sapi/" + id.toString();
    }

    @PostMapping("/tanggalbirahi/add/{id}")
    public String addTanggalBirahi(@PathVariable Long id, @RequestParam("tanggalbirahi") String tanggalBirahi){
        Sapi sapi = sapiRepository.findById(id).get();
        List<Date> tanggal = sapi.getTanggalBirahi();
        tanggal.add(Date.valueOf(tanggalBirahi));
        sapi.setTanggalBirahi(tanggal);
        sapiRepository.save(sapi);
        return "redirect:/sapi/" + id.toString();
    }

    // Tanggal IB endpoint (delete + add)
    @GetMapping("/tanggalib/delete")
    public String deleteTanggalIB(@RequestParam Long id, String tanggalIB){
        Sapi sapi = sapiRepository.findById(id).get();
        List<Date> tanggal = sapi.getTanggalIB();
        tanggal.remove(Date.valueOf(tanggalIB));
        sapi.setTanggalIB(tanggal);
        sapiRepository.save(sapi);
        return "redirect:/sapi/" + id.toString();
    }

    @PostMapping("/tanggalib/add/{id}")
    public String addTanggalIB(@PathVariable Long id, @RequestParam("tanggalib") String tanggalIB){
        Sapi sapi = sapiRepository.findById(id).get();
        List<Date> tanggal = sapi.getTanggalIB();
        tanggal.add(Date.valueOf(tanggalIB));
        sapi.setTanggalIB(tanggal);
        sapiRepository.save(sapi);
        return "redirect:/sapi/" + id.toString();
    }

    // Tanggal Bunting endpoint (delete + add)
    @GetMapping("/tanggalbunting/delete")
    public String deleteTanggalBunting(@RequestParam Long id, String tanggalBunting){
        Sapi sapi = sapiRepository.findById(id).get();
        List<Date> tanggal = sapi.getTanggalBunting();
        tanggal.remove(Date.valueOf(tanggalBunting));
        sapi.setTanggalBunting(tanggal);
        sapiRepository.save(sapi);
        return "redirect:/sapi/" + id.toString();
    }

    @PostMapping("/tanggalbunting/add/{id}")
    public String addTanggalBunting(@PathVariable Long id, @RequestParam("tanggalbunting") String tanggalBunting){
        Sapi sapi = sapiRepository.findById(id).get();
        List<Date> tanggal = sapi.getTanggalBunting();
        tanggal.add(Date.valueOf(tanggalBunting));
        sapi.setTanggalBunting(tanggal);
        sapiRepository.save(sapi);
        return "redirect:/sapi/" + id.toString();
    }

    // Tanggal Pemeriksaan Kebuntingan endpoint (delete + add)
    @GetMapping("/tanggalpemeriksaankebuntingan/delete")
    public String deleteTanggalPemeriksaanKebuntingan(@RequestParam Long id, String tanggalPemeriksaanKebuntingan){
        Sapi sapi = sapiRepository.findById(id).get();
        List<Date> tanggal = sapi.getTanggalPemeriksaanKebuntingan();
        tanggal.remove(Date.valueOf(tanggalPemeriksaanKebuntingan));
        sapi.setTanggalPemeriksaanKebuntingan(tanggal);
        sapiRepository.save(sapi);
        return "redirect:/sapi/" + id.toString();
    }

    @PostMapping("/tanggalpemeriksaankebuntingan/add/{id}")
    public String addTanggalPemeriksaanKebuntingan(@PathVariable Long id, @RequestParam("tanggalpemeriksaankebuntingan") String tanggalPemeriksaanKebuntingan){
        Sapi sapi = sapiRepository.findById(id).get();
        List<Date> tanggal = sapi.getTanggalPemeriksaanKebuntingan();
        tanggal.add(Date.valueOf(tanggalPemeriksaanKebuntingan));
        sapi.setTanggalPemeriksaanKebuntingan(tanggal);
        sapiRepository.save(sapi);
        return "redirect:/sapi/" + id.toString();
    }

    // Kode Straw Semen endpoint (delete + add)
    @GetMapping("/kodestrawsemen/delete")
    public String deleteKodeStrawSemen(@RequestParam Long id, String kodeStrawSemen){
        Sapi sapi = sapiRepository.findById(id).get();
        List<String> kode = sapi.getKodeStrawSemen();
        kode.remove(kodeStrawSemen);
        sapi.setKodeStrawSemen(kode);
        sapiRepository.save(sapi);
        return "redirect:/sapi/" + id.toString();
    }

    @PostMapping("/kodestrawsemen/add/{id}")
    public String addKodeStrawSemen(@PathVariable Long id, @RequestParam("kodestrawsemen") String kodeStrawSemen){
        Sapi sapi = sapiRepository.findById(id).get();
        List<String> kode = sapi.getKodeStrawSemen();
        kode.add(kodeStrawSemen);
        sapi.setKodeStrawSemen(kode);
        sapiRepository.save(sapi);
        return "redirect:/sapi/" + id.toString();
    }
}
