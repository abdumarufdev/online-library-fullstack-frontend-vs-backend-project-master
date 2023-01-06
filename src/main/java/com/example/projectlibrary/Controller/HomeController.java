package com.example.projectlibrary.Controller;

import com.example.projectlibrary.Entayti.*;
import com.example.projectlibrary.Repozitory.*;
import com.example.projectlibrary.Service.KitobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    KitobRepazitory kitobRepazitory;

    @Autowired
    KitobService kitobService;

    @Autowired
    YangilikRepazitory yangilikRepazitory;

    @Autowired
    IjaraBookRepazitory ijaraBookRepazitory;

    @Autowired
    QrcodebookRepazitory qrcodebookRepazitory;

    @Autowired
    XizmatKorsatishRepository xizmatKorsatishRepository;

    @Autowired
    AboutUsRepasitoey aboutUsRepasitoey;

    @Autowired
    RentsRepository rentsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QorzinkaIjaraRepository qorzinkaIjaraRepository;

    @Autowired
    BolimRepository bolimRepository;

    @Autowired
    TadbirlarRepository tadbirlarRepository;

    @Autowired
    TadbirlarSourceRepository sourceRepository;

    @GetMapping("/home")
    public String Home(Model map){

        List<Kitob> bolalar = kitobRepazitory.findByKatagoriya("Bolalar adabitoti");
        List<Kitob> badiy = kitobRepazitory.findByKatagoriya("Badiy adabiyoti");
        List<Kitob> ilmiy = kitobRepazitory.findByKatagoriya("Ilmiy adabitoti");
        List<IjaraBook> bolaar1 = ijaraBookRepazitory.findByKatagoriya("Bolalar adabitoti");
        List<IjaraBook> badiy1 = ijaraBookRepazitory.findByKatagoriya("Badiy adabiyoti");
        List<IjaraBook> ilmiy1 = ijaraBookRepazitory.findByKatagoriya("Ilmiy adabitoti");
        List<Qrcodebook> qrbook = qrcodebookRepazitory.findAll();
        List<AboutUs> aboutUses = aboutUsRepasitoey.findAll();
        List<XizmatKorsatish> xizmatKorsatishList = xizmatKorsatishRepository.findAll();

        List<Tadbirlar> tadbirlars = tadbirlarRepository.findAll();
        for (Tadbirlar t: tadbirlars){
            if (t.getHolat().equals("true")){
                map.addAttribute("Tadbirs",t);
            }
        }

        map.addAttribute("Xizmatlar",xizmatKorsatishList);
        map.addAttribute("BolalarAdabiyot", bolalar);
        map.addAttribute("BadiyAdabiyot", badiy);
        map.addAttribute("IlmiyAdabiyot", ilmiy);
        map.addAttribute("Yangilik",yangilikRepazitory.findAll(PageRequest.of(0,3)));

        map.addAttribute("Bolalar1",bolaar1);
        map.addAttribute("Badiy1",badiy1);
        map.addAttribute("Ilmiy1",ilmiy1);

        map.addAttribute("Qrbook",qrbook);
        map.addAttribute("AboutUs",aboutUses);

        List<Kitob> kitobs = kitobRepazitory.findAll();
        map.addAttribute("OpshiKitoblar",kitobs.size());
        map.addAttribute("Opshibolalar",bolalar.size());
        map.addAttribute("Opshibadiy",badiy.size());
        map.addAttribute("Opshiilmiy",ilmiy.size());

        return "Home/index";
    }

    @GetMapping("/addbook")
    public String Addbooks(Model map){
        List<Kitob> full = kitobRepazitory.findAll();
        map.addAttribute("IlmiyAdabiyot", full);
        return "kitoblar";
    }

    @GetMapping("/buyurtma")
    public String buyurtmaBerish(Model map){
        List<Kitob> jahon = kitobRepazitory.findByKatagoriya("Jahon adabiyoti");
        map.addAttribute("JahonAdabiyot", jahon);
        return "buyurtma";
    }

//    Kitobni yuklab olish

//    @GetMapping("/file/dowlond/{id}")
//    public void DBYuklash(@PathVariable Integer id, HttpServletResponse response) throws IOException {
//        Optional<Kitob> optionalFayl=kitobRepazitory.findById(id);
//        if (optionalFayl.isPresent()){
//            response.setContentType(optionalFayl.get().getKitobSours().getFileContentType());
//            response.setHeader("Content-Disposition","attachment; faylname=\""+optionalFayl.get().getKitobSours().getArginalName()+"\"");
//            FileCopyUtils.copy(optionalFayl.get().getKitobSours().getFileByte(),response.getOutputStream());
//        }
//    }
    @GetMapping("/file/dowlond/{id}")
    public HttpEntity<byte[]> DBYuklash1(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Optional<Kitob> optionalFayl=kitobRepazitory.findById(id);
        if (optionalFayl.isPresent()){
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(optionalFayl.get().getKitobSours().getFileContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + optionalFayl.get().getKitobSours().getArginalName()+"\"")
                    .body(optionalFayl.get().getKitobSours().getFileByte());

        }
        return null;
    }
//   Rasmni olish

    @GetMapping("/image/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<Kitob> byId = kitobRepazitory.findById(id);
        if (byId.isPresent()){
            Kitob kitob = byId.get();
            response.setContentType(kitob.getKitobSours().getContentType());
            response.getOutputStream().write(kitob.getKitobSours().getImageByte());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/image/ijara/{id}")
    @ResponseBody
    void ijarashowimg(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<IjaraBook> byId = ijaraBookRepazitory.findById(id);
        if (byId.isPresent()){
            IjaraBook kitob = byId.get();
            response.setContentType(kitob.getIjaraBookSourse().getContentType());
            response.getOutputStream().write(kitob.getIjaraBookSourse().getImageByte());
            response.getOutputStream().close();
        }
    }


    @GetMapping("/dowlond/{id}")
    public String down(@PathVariable Integer id , Model map){
        List<Kitob> full = kitobRepazitory.findAll();
        for (Kitob i:full) {
            if (i.getId().equals(id)) map.addAttribute("IlmiyAdabiyot", i);
        }
        return "dowlond";
    }

//    @GetMapping("/ijara/kitob")
//    public String zakazBerish(@PathVariable Integer id,Model map){
//        List<IjaraBook> bookList = ijaraBookRepazitory.findAll();
//        for (IjaraBook i : bookList){
//            if (i.getId().equals(id)){
//                map.addAttribute("IjaraKitob",i);
//            }
//        }
//        return "zakaz";
//    }

    @GetMapping("/ijara/{id}/{userId}")
    public String zakaz(@PathVariable Integer id,@PathVariable Integer userId, Model map){
        List<IjaraBook> bookList = ijaraBookRepazitory.findAll();

        for (IjaraBook i : bookList){
            if (i.getId().equals(id)){
                map.addAttribute("IjaraKitob",i);
            }
        }
        List<Userlar> userlars = userRepository.findAll();
        for (Userlar u : userlars){
            if (u.getId().equals(userId)){
               map.addAttribute("IsmvsFam",u.getIsmvsfamilya());
               map.addAttribute("Seriya",u.getSeriya());
               map.addAttribute("Telefon",u.getTelefon());
            }
        }

        map.addAttribute("UserId",userId);
        map.addAttribute("KitobId1",id);
        return "zakaz";
    }

    @GetMapping("/kirish/{id}")
    public String loginAdd(@PathVariable Integer id, Model map){
        map.addAttribute("KitobId",id);
        return "login";
    }

    @GetMapping("/register/{id}")
    public String AddRegister(Model map,@PathVariable Integer id){
        map.addAttribute("KitobId" , id);
        return "register";
    }

    @GetMapping("/contact-us-panel")
    public String panel(Model map){
        List<XizmatKorsatish> list = xizmatKorsatishRepository.findAll();
        map.addAttribute("XizmatKorsatish",list);
        map.addAttribute("Button",list.size());
        return "contact-us-panel";
    }

    @GetMapping("/about-uz-panel")
    public String Aboutuz(Model map){
        List<AboutUs> list = aboutUsRepasitoey.findAll();
        map.addAttribute("AboutUs" , list);
        map.addAttribute("SizeAbout",list.size());
        return "about-uz-panel";
    }

    @GetMapping("/addNew")
    public String AddNew(Model map){
        List<Yangilik> yangiliks = yangilikRepazitory.findAll();
        map.addAttribute("FullYangilik",yangiliks);
        return "addNew";
    }

    @GetMapping("/image/new/{id}")
    @ResponseBody
    void showImage1(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<Yangilik> byId = yangilikRepazitory.findById(id);
        if (byId.isPresent()){
            Yangilik yangilik = byId.get();
            response.setContentType(yangilik.getYangilikSourse().getContentType());
            response.getOutputStream().write(yangilik.getYangilikSourse().getImageByte());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/image/about/{id}")
    @ResponseBody
    void showImage1123(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<AboutUs> byId = aboutUsRepasitoey.findById(id);
        if (byId.isPresent()){
            AboutUs yangilik = byId.get();
            response.setContentType(yangilik.getAboutUsSourse().getContentType());
            response.getOutputStream().write(yangilik.getAboutUsSourse().getRasm());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/ijara/img/{id}")
    @ResponseBody
    void ijaraImgShow(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<IjaraBook> byId = ijaraBookRepazitory.findById(id);
        if (byId.isPresent()){
            IjaraBook yangilik = byId.get();
            response.setContentType(yangilik.getIjaraBookSourse().getContentType());
            response.getOutputStream().write(yangilik.getIjaraBookSourse().getImageByte());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/qr/img/{id}")
    @ResponseBody
    void QRImgShow(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<Qrcodebook> byId = qrcodebookRepazitory.findById(id);
        if (byId.isPresent()){
            Qrcodebook yangilik = byId.get();
            response.setContentType(yangilik.getQrcodebookSourse().getContentType());
            response.getOutputStream().write(yangilik.getQrcodebookSourse().getImageByte());
            response.getOutputStream().close();
        }
    }

    public static void main(String[] args) {
        int a;

    }
    @GetMapping("/admin")
    public String admin_panel(Model map){
        return "admin-panel";
    }
    @GetMapping("/admin-panel")
    public String sss(Model map){
        return "admin-panel";
    }
    @GetMapping("/ijara/books")
    public String ijarabooks(Model map){
        List<IjaraBook> list = ijaraBookRepazitory.findAll();
        map.addAttribute("IjaraBook",list);
        return "ijarabooks";
    }

    @GetMapping("/qr/books")
    public String qrabooks(Model map){
        List<Qrcodebook> list = qrcodebookRepazitory.findAll();
        map.addAttribute("QrcodeBook",list);
        return "qrcodebook";
    }

// =======================   TADBIRLAR   ============================ //

    @GetMapping("/tadbirlar")
    public String tadbirlar(Model map){
        List<Tadbirlar> list = tadbirlarRepository.findAll();
        map.addAttribute("Tadbirlar" , list);
        return "tadbirlar";
    }

    @GetMapping("/tadbirlar/img/{id}")
    @ResponseBody
    void tadbirlarImgShow(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<Tadbirlar> byId = tadbirlarRepository.findById(id);
        if (byId.isPresent()){
            Tadbirlar yangilik = byId.get();
            response.setContentType(yangilik.getTadbirlarSource().getContentType());
            response.getOutputStream().write(yangilik.getTadbirlarSource().getMuqova());
            response.getOutputStream().close();
        }
    }
    @GetMapping("/tadbirlar1/img/{id}")
    @ResponseBody
    void tadbirlarImgShow1(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<Tadbirlar> byId = tadbirlarRepository.findById(id);
        if (byId.isPresent()){
            Tadbirlar yangilik = byId.get();
            response.setContentType(yangilik.getTadbirlarSource().getMuqovaContentType1());
            response.getOutputStream().write(yangilik.getTadbirlarSource().getRasm1());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/tadbirlar2/img/{id}")
    @ResponseBody
    void tadbirlarImgShow2(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<Tadbirlar> byId = tadbirlarRepository.findById(id);
        if (byId.isPresent()){
            Tadbirlar yangilik = byId.get();
            response.setContentType(yangilik.getTadbirlarSource().getMuqovaContentType2());
            response.getOutputStream().write(yangilik.getTadbirlarSource().getRasm2());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/tadbirlar3/img/{id}")
    @ResponseBody
    void tadbirlarImgShow3(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<Tadbirlar> byId = tadbirlarRepository.findById(id);
        if (byId.isPresent()){
            Tadbirlar yangilik = byId.get();
            response.setContentType(yangilik.getTadbirlarSource().getMuqovaContentType3());
            response.getOutputStream().write(yangilik.getTadbirlarSource().getRasm3());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/about/{id}")
    public String About(@PathVariable Integer id, Model map){

        List<Tadbirlar> tadbirlars = tadbirlarRepository.findAll();
        for (Tadbirlar t: tadbirlars){
            if (t.getId().equals(id)){
                map.addAttribute("Tadbirlar",t);
            }
        }

        return "about";
    }




    // =======================  END TADBIRLAR   ============================ //

    // =======================  Tadbirlar   ============================ //



    // ======================= end Tadbirlar   ============================ //

    @GetMapping("/books/{value}")
    public String Books(@PathVariable String value , Model map){

        if (value.equals("0")){
            List<Kitob> full = kitobRepazitory.findAll();
            map.addAttribute("fullBooks", full);
        }
       if (value.equals("badiy")){
           List<Kitob> full = kitobRepazitory.findByKatagoriya("Badiy adabiyoti");
           map.addAttribute("fullBooks", full);
       }
        if (value.equals("bolalar")){
            List<Kitob> full = kitobRepazitory.findByKatagoriya("Bolalar adabitoti");
            map.addAttribute("fullBooks", full);
        }
        if (value.equals("ilmiy")){
            List<Kitob> full = kitobRepazitory.findByKatagoriya("Ilmiy adabitoti");
            map.addAttribute("fullBooks", full);
        }
        if (value.equals("full")){
            List<Kitob> full = kitobRepazitory.findAll();
            map.addAttribute("fullBooks", full);
        }
        return "Books/books";
    }

    @GetMapping("/books")
    public String Books1(Model map){

            List<Kitob> full = kitobRepazitory.findAll();
            map.addAttribute("fullBooks", full);

        return "Books/books";
    }

    @GetMapping("/qrbooks")
    public String qebooks(Model map){
        List<Qrcodebook> full = qrcodebookRepazitory.findAll();
        map.addAttribute("fullBooks", full);
        return "Books/qrbook";
    }

    @GetMapping("/ijara")
    public String ijara(Model map){
        List<IjaraBook> full = ijaraBookRepazitory.findAll();
        map.addAttribute("fullBooks", full);
        return "Books/ijara";
    }

    @GetMapping("/cantact")
    public String Contact(Model map){
        List<XizmatKorsatish> xizmatKorsatishList = xizmatKorsatishRepository.findAll();
        map.addAttribute("Xizmatlar",xizmatKorsatishList);
        return "contact";
    }



    @GetMapping("/news")
    public String News(Model map){
        List<Yangilik> yangiliks = yangilikRepazitory.findAll();
        map.addAttribute("FullNews",yangiliks);
        return "News/news";
    }

    @GetMapping("/users")
    public String Users(Model map){
        List<Userlar> list = userRepository.findAll();
        map.addAttribute("Users",list);
        return "users";
    }

    @GetMapping("/ijaraberilgan")
    public String Ijarachilar(Model map){
        List<Rents> list = rentsRepository.findAll();
        List<QorzinkaIjara> qorzinkaIjaras = qorzinkaIjaraRepository.findAll();
        map.addAttribute("Rents",list);
        map.addAttribute("Qorzinka",qorzinkaIjaras);
        return "berilgan";
    }

//    ============================     //

    @GetMapping("/fullnew")
    public String fullNews(){
        return "News/fullNews";
    }

    @GetMapping("/admin-add-news")
    public String admin_add_news(){
        return "Admin/yangiliklar";
    }

    @GetMapping("/admin-login")
    public String admin_login(){
        return "login1";
    }

    @Autowired
    LoginRepository loginRepository;
    @GetMapping("/parol")
    public String parol(Model map){
        List<Login> list = loginRepository.findAll();
        map.addAttribute("LoginFull",list);
        return "parol";
    }

    // Bo'lim

    @GetMapping("/bolim/one")
    public String BolimOne(Model map){
        List<Bolim> list = bolimRepository.findAll();
        map.addAttribute("BolimOne",list);
        return "form1";
    }

    @GetMapping("/bolim/img/{id}")
    @ResponseBody
    void bolimmgShow(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<Bolim> byId = bolimRepository.findById(id);
        if (byId.isPresent()){
            Bolim yangilik = byId.get();
            response.setContentType(yangilik.getBolimSource().getContentType());
            response.getOutputStream().write(yangilik.getBolimSource().getImageByte());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/event/all")
    public String BolimTwo(Model map){
        List<Tadbirlar> list = tadbirlarRepository.findAll();
        map.addAttribute("AllEvent",list);
        return "form2";
    }

    @GetMapping("/about/us")
    public String BolimThree(Model map){
        List<AboutUs> list = aboutUsRepasitoey.findAll();
        map.addAttribute("AboutUs",list);
        return "form3";
    }

    @GetMapping("/qr/book/{id}")
    public String BolimFour(Model map,@PathVariable Integer id){
        List<Qrcodebook> list = qrcodebookRepazitory.findAll();
        for (Qrcodebook q: list){
            if (q.getId().equals(id)){
                map.addAttribute("QrBooks",q);
            }
        }
        return "form4";
    }

    @GetMapping("/abonement-va-foydalanuvchilarga-xizmat-korsatish-xizmati")
    public String Bolim1(Model map){
        List<Bolim> list = bolimRepository.findAll();
        for (Bolim bolim : list){
            if (bolim.getSarlavha().equals("Abonement va foydalanuvchilarga xizmat ko‘rsatish xizmati")){
                map.addAttribute("Bolim1",bolim);
            }
        }
        return "bolim1";
    }

    @GetMapping("/axborot-bibliografiya-xizmati")
    public String Bolim2(Model map){
        List<Bolim> list = bolimRepository.findAll();
        for (Bolim bolim : list){
            if (bolim.getSarlavha().equals("Axborot-bibliografiya xizmati")){
                map.addAttribute("Bolim2",bolim);
            }
        }
        return "bolim2";
    }

    @GetMapping("/fondlarni-toldirish-saqlash-ishlov-berish-xizmati")
    public String Bolim3(Model map){
        List<Bolim> list = bolimRepository.findAll();
        for (Bolim bolim : list){
            if (bolim.getSarlavha().equals("Fondlarni to‘ldirish, saqlash ishlov berish xizmati")){
                map.addAttribute("Bolim3",bolim);
            }
        }
        return "bolim3";
    }

    @GetMapping("/axborot-kommunikatsiya-texnalogiyalari-va-raqamlashtirish-xizmati")
    public String Bolim4(Model map){
        List<Bolim> list = bolimRepository.findAll();
        for (Bolim bolim : list){
            if (bolim.getSarlavha().equals("Axborot kommunikatsiya texnalogiyalari va raqamlashtirish xizmati")){
                map.addAttribute("Bolim4",bolim);
            }
        }
        return "bolim4";
    }







}
