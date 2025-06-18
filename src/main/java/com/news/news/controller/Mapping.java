package com.news.news.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.news.news.entity.AndhraPradesh;
import com.news.news.entity.DonationSection;
import com.news.news.entity.Epaper;
import com.news.news.entity.Feedback;
import com.news.news.entity.FourthSection;
import com.news.news.entity.HomePageContent;
import com.news.news.entity.News;
import com.news.news.entity.SecondSection;
import com.news.news.entity.ThirdSection;
import com.news.news.entity.UserFeedBack;
import com.news.news.service.AndhraPradeshService;
import com.news.news.service.DonationSectionService;
import com.news.news.service.EpaperService;
import com.news.news.service.FeedbackService;
import com.news.news.service.FourthSectionService;
import com.news.news.service.HomePageContentService;
import com.news.news.service.NewsService;
import com.news.news.service.SecondSectionService;
import com.news.news.service.ThirdSectionService;
import com.news.news.service.UserFeedBackService;

@Controller
public class Mapping {

    public static final String baseUrl = "https://admin.epdcindia.com/uploads/";

    @Autowired
    private HomePageContentService contentService;

    @Autowired
    private SecondSectionService secondSectionService;

    @Autowired
    private ThirdSectionService thirdSectionService;

    @Autowired
    private FourthSectionService fourthSectionService;

    @Autowired
    private DonationSectionService donationSectionService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserFeedBackService userfeedbackService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private EpaperService epaperService;

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<HomePageContent> contentList = contentService.getAllContent();
        List<SecondSection> secondSections = secondSectionService.getAllContent();
        List<ThirdSection> thirdSections = thirdSectionService.getAllContent();
        List<FourthSection> fourthSections = fourthSectionService.getAllContent();
        List<News> andhraNews = newsService.getNewsByCategory("Andhrapradesh");
        List<News> telanganaNews = newsService.getNewsByCategory("Telangana");
        List<News> moviesNews = newsService.getNewsByCategory("Movies");
        List<News> sportsNews = newsService.getNewsByCategory("sports");

        // Construct full URLs for images and videos
        for (HomePageContent content : contentList) {
            content.setMainImage(constructImageUrl(content.getMainImage()));
            content.setSidebar1Image(constructImageUrl(content.getSidebar1Image()));
            content.setSidebar2Image(constructImageUrl(content.getSidebar2Image()));
            content.setSidebar3Image(constructImageUrl(content.getSidebar3Image()));
        }

        for (SecondSection section : secondSections) {
            section.setLeftImage1(constructImageUrl(section.getLeftImage1()));
            section.setLeftImage2(constructImageUrl(section.getLeftImage2()));
            section.setLeftImage3(constructImageUrl(section.getLeftImage3()));
            section.setVideo1(section.getVideo1());
            System.out.println("---------------------------------");
            System.out.println(section.getVideo1());
            System.out.println("---------------------------------");
            section.setVideo2(section.getVideo2());
            System.out.println(section.getVideo2());
            section.setVideo3(section.getVideo3());
            System.out.println(section.getVideo3());
        }

        for (ThirdSection section : thirdSections) {
            section.setImage1(constructImageUrl(section.getImage1()));
            section.setImage2(constructImageUrl(section.getImage2()));
            section.setImage3(constructImageUrl(section.getImage3()));
            section.setImage4(constructImageUrl(section.getImage4()));
            section.setImage5(constructImageUrl(section.getImage5()));
            section.setImage6(constructImageUrl(section.getImage6()));
        }

        for (FourthSection section : fourthSections) {
            section.setImage1(constructImageUrl(section.getImage1()));
            section.setImage2(constructImageUrl(section.getImage2()));
            section.setImage3(constructImageUrl(section.getImage3()));
            section.setImage4(constructImageUrl(section.getImage4()));
            section.setImage5(constructImageUrl(section.getImage5()));
            section.setImage6(constructImageUrl(section.getImage6()));
            section.setImage7(constructImageUrl(section.getImage7()));
            section.setImage8(constructImageUrl(section.getImage8()));
            section.setImage9(constructImageUrl(section.getImage9()));
            section.setImage10(constructImageUrl(section.getImage10()));
            section.setImage11(constructImageUrl(section.getImage11()));
            section.setImage12(constructImageUrl(section.getImage12()));
            section.setImage13(constructImageUrl(section.getImage13()));
            section.setImage14(constructImageUrl(section.getImage14()));
            section.setImage15(constructImageUrl(section.getImage15()));
        }

        // Construct full URLs for AndhraSection
        andhraNews.forEach(this::constructImageUrls);
        telanganaNews.forEach(this::constructImageUrls);
        moviesNews.forEach(this::constructImageUrls);
        sportsNews.forEach(this::constructImageUrls);

        model.addAttribute("contentList", contentList);
        model.addAttribute("secondSections", secondSections);
        model.addAttribute("thirdSections", thirdSections);
        model.addAttribute("fourthSections", fourthSections);
        model.addAttribute("AndhraNews", andhraNews);
        model.addAttribute("TelanganaNews", telanganaNews);
        model.addAttribute("moviesNews", moviesNews);
        model.addAttribute("sportsNews", sportsNews);

        // Fetch the latest Epaper data
        Epaper epaper = epaperService.getLatestContent();
        // List<HomePageContent> contentList = contentService.getAllContent();

        // Construct full URLs for images and PDFs
        if (epaper != null) {
            String imageBaseUrl = "https://admin.epdcindia.com/uploads/";
            String pdfBaseUrl = "https://admin.epdcindia.com/uploads/";

            epaper.setEdition1Image(constructFileUrl(epaper.getEdition1Image(), imageBaseUrl));
            epaper.setEdition1PdfFile(constructFileUrl(epaper.getEdition1PdfFile(), pdfBaseUrl));
            epaper.setEdition2Image(constructFileUrl(epaper.getEdition2Image(), imageBaseUrl));
            epaper.setEdition2PdfFile(constructFileUrl(epaper.getEdition2PdfFile(), pdfBaseUrl));
            epaper.setAdvertisementImage(constructFileUrl(epaper.getAdvertisementImage(), imageBaseUrl));
        }

        // Add the Epaper object to the model
        model.addAttribute("epaper", epaper);
        model.addAttribute("contentList", contentList);

        // Fetch only approved feedback from the database
        List<UserFeedBack> approvedFeedback = userfeedbackService.getApprovedFeedback();

        // Pass the approved feedback list to the model
        model.addAttribute("approvedFeedback", approvedFeedback);

        return "index";
    }

    @GetMapping("/category")
    public String showCategoryPage(
            @RequestParam String category, // Dynamic category parameter
            Model model) {

        // Handle null or empty category
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category parameter is required.");
        }

        // Fetch all content and news for the specified category
        List<HomePageContent> contentList = contentService.getAllContent();
        List<News> newsList = newsService.getNewsByCategory(category);

        // Handle no news found
        if (newsList.isEmpty()) {
            throw new RuntimeException("No news found for category: " + category);
        }

        // Construct full URLs for images
        newsList.forEach(this::constructImageUrls);

        // Add data to the model
        model.addAttribute("contentList", contentList);
        model.addAttribute("newsList", newsList);
        model.addAttribute("category", category);

        // Dynamically return the appropriate HTML page based on the category
        switch (category) {
            case "Andhrapradesh":
                return "andhrapradesh";
            case "Telangana":
                return "telangana";
            case "Environment":
                return "environment";
            case "Movies":
                return "movies";
            case "Sports":
                return "sports";
            case "Business":
                return "business";
            case "Family":
                return "family";
            case "Prediction":
                return "prediction";
            case "Health":
                return "health";
            case "Education":
                return "education";
            case "Blog":
                return "blog";
            default:
                throw new IllegalArgumentException("Invalid category: " + category);
        }
    }

    @GetMapping("/detailsadd/{id}")
    public String addPage(@PathVariable String id,
            @RequestParam(required = false) String addtext,
            Model model) {
        // Fetch the content by ID
        HomePageContent content = contentService.getContentById(id);

        // Construct the full URL for adImage
        String baseUrl = "https://admin.epdcindia.com/uploads/";
        String adImage = content.getAdImage();
        if (adImage != null && !adImage.startsWith("http")) {
            adImage = baseUrl + adImage; // Prepend the base URL if necessary
        }

        // Add the required fields to the model
        if (addtext != null && addtext.equals("true")) {
            model.addAttribute("addText", content.getAddText());
            model.addAttribute("adLink", content.getAdLink());
            model.addAttribute("adImage", adImage); // Use the constructed URL
        }

        // Return the Thymeleaf template name
        return "addtext-detail";
    }

    @GetMapping("/details/{id}")
    public String showDetailPage(@PathVariable String id,
            @RequestParam(required = false) String sidebar,
            @RequestParam(required = false) String second,
            @RequestParam(required = false) String third,
            @RequestParam(required = false) String fourth,
            Model model) {
        List<HomePageContent> contentList = contentService.getAllContent();

        List<ThirdSection> thirdSections = thirdSectionService.getAllContent();
        for (ThirdSection section : thirdSections) {
            section.setImage1(constructImageUrl(section.getImage1()));
            section.setImage2(constructImageUrl(section.getImage2()));
            section.setImage3(constructImageUrl(section.getImage3()));
            section.setImage4(constructImageUrl(section.getImage4()));
            section.setImage5(constructImageUrl(section.getImage5()));
            section.setImage6(constructImageUrl(section.getImage6()));
        }
        model.addAttribute("thirdSections", thirdSections);

        // Default values
        String image = "";
        String title = "";
        String text = "";
        String addText = "";
        boolean isVideo = false; // Flag to determine if the content is a video

        // Logic for HomePageContent (default case when only ID is provided)
        if (sidebar == null && second == null && third == null && fourth == null) {
            HomePageContent content = contentService.getContentById(id);
            if (content == null) {
                throw new RuntimeException("HomePageContent not found");
            }

            image = constructImageUrl(content.getMainImage());
            text = content.getMainText();
            addText = content.getAddText();
        }
        // Logic for Sidebar
        else if (sidebar != null) {
            HomePageContent content = contentService.getContentById(id);
            if (content == null) {
                throw new RuntimeException("HomePageContent not found");
            }

            image = constructImageUrl(content.getMainImage());
            title = "Main Content";
            text = content.getMainText();
            addText = content.getAddText();

            // Sidebar logic
            if ("item1".equals(sidebar)) {
                image = constructImageUrl(content.getSidebar1Image());
                title = content.getSidebar1Title();
                text = content.getSidebar1Text();
            } else if ("item2".equals(sidebar)) {
                image = constructImageUrl(content.getSidebar2Image());
                title = content.getSidebar2Title();
                text = content.getSidebar2Text();
            } else if ("item3".equals(sidebar)) {
                image = constructImageUrl(content.getSidebar3Image());
                title = content.getSidebar3Title();
                text = content.getSidebar3Text();
            }
        }
        // Logic for SecondSection
        else if (second != null) {
            SecondSection section = secondSectionService.getContentById(id);
            if (section == null) {
                throw new RuntimeException("SecondSection not found");
            }

            switch (second) {
                case "left1":
                    image = constructImageUrl(section.getLeftImage1());
                    title = "Latest News";
                    text = section.getLeftText1();
                    break;
                case "left2":
                    image = constructImageUrl(section.getLeftImage2());
                    title = "Latest News";
                    text = section.getLeftText2();
                    break;
                case "left3":
                    image = constructImageUrl(section.getLeftImage3());
                    title = "Latest News";
                    text = section.getLeftText3();
                    break;
                case "video1":
                    image = section.getVideo1();
                    isVideo = true; // Set isVideo to true for videos
                    break;
                case "video2":
                    image = section.getVideo2();
                    title = "Trending Video";
                    text = section.getVideoText2();
                    isVideo = true; // Set isVideo to true for videos
                    break;
                case "video3":
                    image = section.getVideo3();
                    title = "Trending Video";
                    text = section.getVideoText3();
                    isVideo = true; // Set isVideo to true for videos
                    break;
                default:
                    throw new RuntimeException("Invalid second parameter");
            }
        }
        // Logic for ThirdSection
        else if (third != null) {
            ThirdSection section = thirdSectionService.getContentById(id);
            if (section == null) {
                throw new RuntimeException("ThirdSection not found");
            }

            switch (third) {
                case "item1":
                    image = constructImageUrl(section.getImage1());
                    title = section.getTitle1();
                    text = section.getText1();
                    break;
                case "item2":
                    image = constructImageUrl(section.getImage2());
                    title = section.getTitle2();
                    text = section.getText2();
                    break;
                case "item3":
                    image = constructImageUrl(section.getImage3());
                    title = section.getTitle3();
                    text = section.getText3();
                    break;
                case "item4":
                    image = constructImageUrl(section.getImage4());
                    title = section.getTitle4();
                    text = section.getText4();
                    break;
                case "item5":
                    image = constructImageUrl(section.getImage5());
                    title = section.getTitle5();
                    text = section.getText5();
                    break;
                case "item6":
                    image = constructImageUrl(section.getImage6());
                    title = section.getTitle6();
                    text = section.getText6();
                    break;
                default:
                    throw new RuntimeException("Invalid third parameter");
            }
        }
        // Logic for FourthSection (Only Images)
        else if (fourth != null) {
            FourthSection section = fourthSectionService.getContentById(id);
            if (section == null) {
                throw new RuntimeException("FourthSection not found");
            }

            switch (fourth) {
                case "item1":
                    image = constructImageUrl(section.getImage1());
                    break;
                case "item2":
                    image = constructImageUrl(section.getImage2());
                    break;
                case "item3":
                    image = constructImageUrl(section.getImage3());
                    break;
                case "item4":
                    image = constructImageUrl(section.getImage4());
                    break;
                case "item5":
                    image = constructImageUrl(section.getImage5());
                    break;
                case "item6":
                    image = constructImageUrl(section.getImage6());
                    break;
                case "item7":
                    image = constructImageUrl(section.getImage7());
                    break;
                case "item8":
                    image = constructImageUrl(section.getImage8());
                    break;
                case "item9":
                    image = constructImageUrl(section.getImage9());
                    break;
                case "item10":
                    image = constructImageUrl(section.getImage10());
                    break;
                case "item11":
                    image = constructImageUrl(section.getImage11());
                    break;
                case "item12":
                    image = constructImageUrl(section.getImage12());
                    break;
                case "item13":
                    image = constructImageUrl(section.getImage13());
                    break;
                case "item14":
                    image = constructImageUrl(section.getImage14());
                    break;
                case "item15":
                    image = constructImageUrl(section.getImage15());
                    break;
                case "item16":
                    image = constructImageUrl(section.getImage16());
                    break;
                case "item17":
                    image = constructImageUrl(section.getImage17());
                    break;
                case "item18":
                    image = constructImageUrl(section.getImage18());
                    break;
                case "item19":
                    image = constructImageUrl(section.getImage19());
                    break;
                case "item20":
                    image = constructImageUrl(section.getImage20());
                    break;
                case "item21":
                    image = constructImageUrl(section.getImage21());
                    break;
                case "item22":
                    image = constructImageUrl(section.getImage22());
                    break;
                case "item23":
                    image = constructImageUrl(section.getImage23());
                    break;
                case "item24":
                    image = constructImageUrl(section.getImage24());
                    break;
                case "item25":
                    image = constructImageUrl(section.getImage25());
                    break;
                case "item26":
                    image = constructImageUrl(section.getImage26());
                    break;
                case "item28":
                    image = constructImageUrl(section.getImage28());
                    break;
                case "item29":
                    image = constructImageUrl(section.getImage29());
                    break;
                case "item30":
                    image = constructImageUrl(section.getImage30());
                    break;
                case "item31":
                    image = constructImageUrl(section.getImage31());
                    break;
                case "item32":
                    image = constructImageUrl(section.getImage32());
                    break;
                case "item33":
                    image = constructImageUrl(section.getImage33());
                    break;
                case "item34":
                    image = constructImageUrl(section.getImage34());
                    break;
                case "item35":
                    image = constructImageUrl(section.getImage35());
                    break;
                case "item36":
                    image = constructImageUrl(section.getImage36());
                    break;
                case "item37":
                    image = constructImageUrl(section.getImage37());
                    break;
                case "item38":
                    image = constructImageUrl(section.getImage38());
                    break;
                case "item39":
                    image = constructImageUrl(section.getImage39());
                    break;
                case "item40":
                    image = constructImageUrl(section.getImage40());
                    break;
                default:
                    throw new RuntimeException("Invalid fourth parameter");
            }

            // Clear title and text as we only need images
            title = "";
            text = "";
        } else {
            throw new RuntimeException(
                    "Invalid request: neither sidebar, second, third, fourth, nor andhra parameter provided");
        }

        model.addAttribute("mainImage", image);
        model.addAttribute("mainText", text);
        model.addAttribute("title", title);
        model.addAttribute("addText", addText);
        model.addAttribute("isVideo", isVideo); // Add isVideo attribute to the model
        model.addAttribute("contentList", contentList);

        return "detail";
    }

    @GetMapping("/detail/{id}")
    public String showDetailPage2(@PathVariable String id,
            @RequestParam String category,
            @RequestParam(required = false) String section,
            Model model) {
        List<HomePageContent> contentList = contentService.getAllContent();

        List<News> newsList = newsService.getNewsByCategory(category);
        if (newsList.isEmpty()) {
            throw new RuntimeException("No news found for category: " + category);
        }

        newsList.forEach(this::constructImageUrls);
        model.addAttribute("newsList", newsList);

        // Default values
        String image = "";
        String title = "";
        String text = "";
        String addText = "";
        boolean isVideo = false; // Flag to determine if the content is a video

        // Fetch the latest news for the specified category
        News news = newsService.getLatestNewsByCategory(category);
        if (news == null) {
            throw new RuntimeException("No news found for category: " + category);
        }

        // Construct full URLs for images
        constructImageUrls(news);

        // Logic for different sections
        if (section != null) {
            switch (section) {
                case "sidebar1":
                    image = news.getSidebar1Image();
                    title = news.getSidebar1Title();
                    text = news.getSidebar1Text();
                    break;
                case "sidebar2":
                    image = news.getSidebar2Image();
                    title = news.getSidebar2Title();
                    text = news.getSidebar2Text();
                    break;
                case "sidebar3":
                    image = news.getSidebar3Image();
                    title = news.getSidebar3Title();
                    text = news.getSidebar3Text();
                    break;
                case "sidebar4":
                    image = news.getSidebar4Image();
                    title = news.getSidebar4Title();
                    text = news.getSidebar4Text();
                    break;
                case "sidebar5":
                    image = news.getSidebar5Image();
                    title = news.getSidebar5Title();
                    text = news.getSidebar5Text();
                    break;
                case "sidebar6":
                    image = news.getSidebar6Image();
                    title = news.getSidebar6Title();
                    text = news.getSidebar6Text();
                    break;
                case "sidebar7":
                    image = news.getSidebar7Image();
                    title = news.getSidebar7Title();
                    text = news.getSidebar7Text();
                    break;
                case "sidebar8":
                    image = news.getSidebar8Image();
                    title = news.getSidebar8Title();
                    text = news.getSidebar8Text();
                    break;
                case "sidebar9":
                    image = news.getSidebar9Image();
                    title = news.getSidebar9Title();
                    text = news.getSidebar9Text();
                    break;
                case "sidebar10":
                    image = news.getSidebar10Image();
                    title = news.getSidebar10Title();
                    text = news.getSidebar10Text();
                    break;
                case "sidebar11":
                    image = news.getSidebar11Image();
                    title = news.getSidebar11Title();
                    text = news.getSidebar11Text();
                    break;
                case "sidebar12":
                    image = news.getSidebar12Image();
                    title = news.getSidebar12Title();
                    text = news.getSidebar12Text();
                    break;
                case "sidebar13":
                    image = news.getSidebar13Image();
                    title = news.getSidebar13Title();
                    text = news.getSidebar13Text();
                    break;
                case "sidebar14":
                    image = news.getSidebar14Image();
                    title = news.getSidebar14Title();
                    text = news.getSidebar14Text();
                    break;
                case "sidebar15":
                    image = news.getSidebar15Image();
                    title = news.getSidebar15Title();
                    text = news.getSidebar15Text();
                    break;
                case "sidebar16":
                    image = news.getSidebar16Image();
                    title = news.getSidebar16Title();
                    text = news.getSidebar16Text();
                    break;
                case "sidebar17":
                    image = news.getSidebar17Image();
                    title = news.getSidebar17Title();
                    text = news.getSidebar17Text();
                    break;
                case "sidebar18":
                    image = news.getSidebar18Image();
                    title = news.getSidebar18Title();
                    text = news.getSidebar18Text();
                    break;
                case "sidebar19":
                    image = news.getSidebar19Image();
                    title = news.getSidebar19Title();
                    text = news.getSidebar19Text();
                    break;
                case "sidebar20":
                    image = news.getSidebar20Image();
                    title = news.getSidebar20Title();
                    text = news.getSidebar20Text();
                    break;

                case "right1":
                    image = news.getRightImage1();
                    break;
                case "right2":
                    image = news.getRightImage2();
                    break;

                case "item1":
                    image = news.getImage1();
                    title = news.getTitle1();
                    text = news.getText1();
                    break;
                case "item2":
                    image = news.getImage2();
                    title = news.getTitle2();
                    text = news.getText2();
                    break;
                case "item3":
                    image = news.getImage3();
                    title = news.getTitle3();
                    text = news.getText3();
                    break;
                case "item4":
                    image = news.getImage4();
                    title = news.getTitle4();
                    text = news.getText4();
                    break;
                case "item5":
                    image = news.getImage5();
                    title = news.getTitle5();
                    text = news.getText5();
                    break;
                case "item6":
                    image = news.getImage6();
                    title = news.getTitle6();
                    text = news.getText6();
                    break;
                case "item7":
                    image = news.getImage7();
                    title = news.getTitle7();
                    text = news.getText7();
                    break;
                case "item8":
                    image = news.getImage8();
                    title = news.getTitle8();
                    text = news.getText8();
                    break;
                case "item9":
                    image = news.getImage9();
                    title = news.getTitle9();
                    text = news.getText9();
                    break;
                case "item10":
                    image = news.getImage10();
                    title = news.getTitle10();
                    text = news.getText10();
                    break;
                default:
                    throw new RuntimeException("Invalid section parameter: " + section);
            }
        } else {
            throw new RuntimeException("Invalid request: section parameter is required");
        }

        model.addAttribute("mainImage", image);
        model.addAttribute("mainText", text);
        model.addAttribute("title", title);
        model.addAttribute("addText", addText);
        model.addAttribute("isVideo", isVideo); // Add isVideo attribute to the model
        model.addAttribute("contentList", contentList);
        model.addAttribute("category", category);

        return "details-news";
    }

    @GetMapping("/detail")
    public String detail() {
        return "detail";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        List<HomePageContent> contentList = contentService.getAllContent();
        model.addAttribute("contentList", contentList);
        return "contact";
    }

    @GetMapping("/privacy")
    public String privacy(Model model) {
        List<HomePageContent> contentList = contentService.getAllContent();
        model.addAttribute("contentList", contentList);
        return "privacy";
    }

    @GetMapping("/details-news")
    public String details() {
        return "details-news";
    }

    @GetMapping("/epaper")
    public String epaper(Model model) {

        // Fetch the latest Epaper data
        Epaper epaper = epaperService.getLatestContent();
        List<HomePageContent> contentList = contentService.getAllContent();

        // Construct full URLs for images and PDFs
        if (epaper != null) {
            String imageBaseUrl = "https://admin.epdcindia.com/uploads/";
            String pdfBaseUrl = "https://admin.epdcindia.com/uploads/";

            epaper.setEdition1Image(constructFileUrl(epaper.getEdition1Image(), imageBaseUrl));
            epaper.setEdition1PdfFile(constructFileUrl(epaper.getEdition1PdfFile(), pdfBaseUrl));
            epaper.setEdition2Image(constructFileUrl(epaper.getEdition2Image(), imageBaseUrl));
            epaper.setEdition2PdfFile(constructFileUrl(epaper.getEdition2PdfFile(), pdfBaseUrl));
            epaper.setAdvertisementImage(constructFileUrl(epaper.getAdvertisementImage(), imageBaseUrl));
        }

        // Add the Epaper object to the model
        model.addAttribute("epaper", epaper);
        model.addAttribute("contentList", contentList);

        return "epaper";
    }

    @GetMapping("/gallery")
    public String gallery(Model model) {
        List<HomePageContent> contentList = contentService.getAllContent();
        List<FourthSection> fourthSections = fourthSectionService.getAllContent();

        // Construct full URLs for images in FourthSection
        for (FourthSection section : fourthSections) {
            section.setImage1(constructImageUrl(section.getImage1()));
            section.setImage2(constructImageUrl(section.getImage2()));
            section.setImage3(constructImageUrl(section.getImage3()));
            section.setImage4(constructImageUrl(section.getImage4()));
            section.setImage5(constructImageUrl(section.getImage5()));
            section.setImage6(constructImageUrl(section.getImage6()));
            section.setImage7(constructImageUrl(section.getImage7()));
            section.setImage8(constructImageUrl(section.getImage8()));
            section.setImage9(constructImageUrl(section.getImage9()));
            section.setImage10(constructImageUrl(section.getImage10()));
            section.setImage11(constructImageUrl(section.getImage11()));
            section.setImage12(constructImageUrl(section.getImage12()));
            section.setImage13(constructImageUrl(section.getImage13()));
            section.setImage14(constructImageUrl(section.getImage14()));
            section.setImage15(constructImageUrl(section.getImage15()));
            section.setImage15(constructImageUrl(section.getImage16()));
            section.setImage15(constructImageUrl(section.getImage17()));
            section.setImage15(constructImageUrl(section.getImage18()));
            section.setImage15(constructImageUrl(section.getImage19()));
            section.setImage15(constructImageUrl(section.getImage20()));
            section.setImage1(constructImageUrl(section.getImage21()));
            section.setImage2(constructImageUrl(section.getImage22()));
            section.setImage3(constructImageUrl(section.getImage23()));
            section.setImage4(constructImageUrl(section.getImage24()));
            section.setImage5(constructImageUrl(section.getImage25()));
            section.setImage6(constructImageUrl(section.getImage26()));
            section.setImage7(constructImageUrl(section.getImage27()));
            section.setImage8(constructImageUrl(section.getImage28()));
            section.setImage9(constructImageUrl(section.getImage29()));
            section.setImage10(constructImageUrl(section.getImage30()));
            section.setImage1(constructImageUrl(section.getImage31()));
            section.setImage2(constructImageUrl(section.getImage32()));
            section.setImage3(constructImageUrl(section.getImage33()));
            section.setImage4(constructImageUrl(section.getImage34()));
            section.setImage5(constructImageUrl(section.getImage35()));
            section.setImage6(constructImageUrl(section.getImage36()));
            section.setImage7(constructImageUrl(section.getImage37()));
            section.setImage8(constructImageUrl(section.getImage38()));
            section.setImage9(constructImageUrl(section.getImage39()));
            section.setImage10(constructImageUrl(section.getImage40()));
        }

        model.addAttribute("contentList", contentList);
        model.addAttribute("fourthSections", fourthSections);

        return "gallery";
    }

    // Helper method to construct full URLs for images
    private String constructImageUrl(String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) {
            return ""; // Return an empty string or a default image URL
        }
        return baseUrl + imagePath.replace("\\", "/");
    }

    // Helper method to construct full URLs for files (images or PDFs)
    private String constructFileUrl(String filePath, String baseUrl) {
        if (filePath == null || filePath.isEmpty()) {
            return ""; // Return an empty string or a default URL
        }
        return baseUrl + filePath.replace("\\", "/");
    }

    // Helper method to construct full URLs for Epaper images and PDFs
    private void constructEpaperUrls(Epaper epaper, String imageBaseUrl, String pdfBaseUrl) {
        epaper.setEdition1Image(constructFileUrl(epaper.getEdition1Image(), imageBaseUrl));
        epaper.setEdition1PdfFile(constructFileUrl(epaper.getEdition1PdfFile(), pdfBaseUrl));
        epaper.setEdition2Image(constructFileUrl(epaper.getEdition2Image(), imageBaseUrl));
        epaper.setEdition2PdfFile(constructFileUrl(epaper.getEdition2PdfFile(), pdfBaseUrl));
        epaper.setAdvertisementImage(constructFileUrl(epaper.getAdvertisementImage(), imageBaseUrl));
    }

    // Helper method to construct full URLs for News images
    private void constructImageUrls(News news) {
        news.setSidebar1Image(constructImageUrl(news.getSidebar1Image()));
        news.setSidebar2Image(constructImageUrl(news.getSidebar2Image()));
        news.setSidebar3Image(constructImageUrl(news.getSidebar3Image()));
        news.setSidebar4Image(constructImageUrl(news.getSidebar4Image()));
        news.setSidebar5Image(constructImageUrl(news.getSidebar5Image()));
        news.setSidebar6Image(constructImageUrl(news.getSidebar6Image()));
        news.setSidebar7Image(constructImageUrl(news.getSidebar7Image()));
        news.setSidebar8Image(constructImageUrl(news.getSidebar8Image()));
        news.setSidebar9Image(constructImageUrl(news.getSidebar9Image()));
        news.setSidebar10Image(constructImageUrl(news.getSidebar10Image()));

        news.setSidebar11Image(constructImageUrl(news.getSidebar11Image()));
        news.setSidebar12Image(constructImageUrl(news.getSidebar12Image()));
        news.setSidebar13Image(constructImageUrl(news.getSidebar13Image()));
        news.setSidebar14Image(constructImageUrl(news.getSidebar14Image()));
        news.setSidebar15Image(constructImageUrl(news.getSidebar15Image()));
        news.setSidebar16Image(constructImageUrl(news.getSidebar16Image()));
        news.setSidebar17Image(constructImageUrl(news.getSidebar17Image()));
        news.setSidebar18Image(constructImageUrl(news.getSidebar18Image()));
        news.setSidebar19Image(constructImageUrl(news.getSidebar19Image()));
        news.setSidebar20Image(constructImageUrl(news.getSidebar20Image()));

        news.setRightImage1(constructImageUrl(news.getRightImage1()));
        news.setRightImage2(constructImageUrl(news.getRightImage2()));

        news.setImage1(constructImageUrl(news.getImage1()));
        news.setImage2(constructImageUrl(news.getImage2()));
        news.setImage3(constructImageUrl(news.getImage3()));
        news.setImage4(constructImageUrl(news.getImage4()));
        news.setImage5(constructImageUrl(news.getImage5()));
        news.setImage6(constructImageUrl(news.getImage6()));
        news.setImage7(constructImageUrl(news.getImage7()));
        news.setImage8(constructImageUrl(news.getImage8()));
        news.setImage9(constructImageUrl(news.getImage9()));
        news.setImage10(constructImageUrl(news.getImage10()));

    }

    @GetMapping("/search")
    public String search(@RequestParam("category") String category) {
        // Redirect to the respective category page
        switch (category.toLowerCase()) {
            case "andhrapradesh":
                return "redirect:/category?category=Andhrapradesh";
            case "telangana":
                return "redirect:/category?category=Telangana";
            case "movies":
                return "redirect:/category?category=Movies";
            case "environment":
                return "redirect:/category?category=Environment";
            case "sports":
                return "redirect:/category?category=Sports";
            case "business":
                return "redirect:/category?category=Business";
            case "family":
                return "redirect:/category?category=Family";
            case "prediction":
                return "redirect:/category?category=Prediction";
            case "health":
                return "redirect:/category?category=Health";
            case "education":
                return "redirect:/category?category=Education";
            default:
                return "redirect:/error?message=No results found for: " + category;
        }
    }

    @GetMapping("donation")
    public String donation(Model model) {
        List<HomePageContent> contentList = contentService.getAllContent();
        model.addAttribute("contentList", contentList);

        DonationSection donation = donationSectionService.getLatestContent();
        // Construct full URLs for images and PDFs
        if (donation != null) {
            String imageBaseUrl = "https://admin.epdcindia.com/uploads/";
            donation.setImage(constructFileUrl(donation.getImage(), imageBaseUrl));
        }

        // Add the Epaper object to the model
        model.addAttribute("donation", donation);

        // Check if feedbackSuccess is already in the model (from redirect)
        if (!model.containsAttribute("feedbackSuccess")) {
            model.addAttribute("feedbackSuccess", false); // Default to false
        }
        // Fetch only approved feedback from the database
        List<Feedback> approvedFeedback = feedbackService.getApprovedFeedback();

        // Pass the approved feedback list to the model
        model.addAttribute("approvedFeedback", approvedFeedback);

        return "donation";
    }

    // Endpoint to submit feedback
    @PostMapping("/submit-feedback")
    public String submitFeedback(@ModelAttribute Feedback feedback, RedirectAttributes redirectAttributes) {
        // Save the feedback
        feedbackService.saveFeedback(feedback);

        // Add flash attribute for success message
        redirectAttributes.addFlashAttribute("feedbackSuccess", true);

        // Redirect back to the donation page
        return "redirect:/donation";
    }

    @PostMapping("/submit-feedback-user")
    public String submitUserFeedback(@ModelAttribute UserFeedBack feedback, RedirectAttributes redirectAttributes) {
        // Save the feedback
        userfeedbackService.saveFeedback(feedback);

        // Add flash attribute for success message
        redirectAttributes.addFlashAttribute("feedbackSuccess", true);

        // Redirect to the contact page
        return "redirect:/contact";
    }

}