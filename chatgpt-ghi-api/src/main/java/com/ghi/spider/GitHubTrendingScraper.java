package com.ghi.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author LGX_TvT <br>
 * @version 1.0 <br>
 * Create by 2023-03-22 15:31 <br>
 * @description: GitHubTrendingScraper <br>
 */
public class GitHubTrendingScraper {
    public static void main(String[] args) {
        String url = "https://github.com/trending";
        try {
            // 发送GET请求以获取GitHub Trending页面的HTML代码
            Document doc = Jsoup.connect(url).get();

            // 使用JSoup解析HTML代码，并从中提取项目数据
            Elements repoList = doc.select("article.Box-row");
            for (int i = 0; i < repoList.size(); i++) {
                String repoTitle = repoList.get(i).select("h1 a").text();
                String repoUrl = "https://github.com" + repoList.get(i).select("h1 a").attr("href");
                String repoDescription = repoList.get(i).select("p").text();
                String repoLanguage = repoList.get(i).select("[itemprop=programmingLanguage]").text();
                String repoStars = repoList.get(i).select(".octicon-star").parents().get(0).text().trim();
                String repoForks = repoList.get(i).select(".octicon-repo-forked").parents().get(0).text().trim();

                // 输出提取的项目数据
                System.out.println("Repo: " + repoTitle);
                System.out.println("URL: " + repoUrl);
                System.out.println("Description: " + repoDescription);
                System.out.println("Language: " + repoLanguage);
                System.out.println("Stars: " + repoStars);
                System.out.println("Forks: " + repoForks);
                System.out.println("-----------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

