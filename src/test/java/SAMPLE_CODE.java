/**
 * Copyright (c) 2015-present, Facebook, Inc. All rights reserved.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 *
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */

import com.facebook.ads.sdk.*;
import java.io.File;
import java.util.Arrays;

public class SAMPLE_CODE {
  public static void main (String args[]) throws APIException {

      // EAAcgjp5DG4ABAKgVSB1O8K6Eg0wBTkd1qjiWKKAVBtqcAiByRocQy8ZCYjUsjYLDPRK4m4ZBKC5htTkTzwLCv6d0vJcqtPdDomOvDLquHKQ6SwFZCZAWgzIeCKDdLU2cRz8CqNVFjMwVqsFHDu4ZB5K4sJ03JcpFM0b3gZCMMvcACs0pguMXsSjzcLC5wPMxwnlZB3Svz2NzJTMpwcYYlvpwLHL6ZArYV8UZD
    String access_token = "EAAcgjp5DG4ABAHfuckxFm68NDvcMEZB3kogfe1cpqWEf57X0Eh6RIGgVJZCDpUZC7QiYrQL2ovUXygdhzQhMkyEhorF5NB9qtPE5aZC9IKKs6scZBHWVr0umpAfP3xWZA5KviIZC7jwv18d2TVwc9rdBa9VeDIqz3ZCG7EQnAe6ebvdBmqDh69zRzSgwEOpbHSI5qyAtdrQYnFD4ZCCAXClC9ZCPJdNC7edncZD";
    String ad_account_id = "105185726862943";
    String app_secret = "80cb08059c6b58192724e82080f45a17";
    String page_id = "112357469426135";
    APIContext context = new APIContext(access_token).enableDebug(true);

    Campaign campaign = new AdAccount(ad_account_id, context).createCampaign()
      .setName("My Campaign")
      .setBuyingType("AUCTION")
      .setObjective(Campaign.EnumObjective.VALUE_PAGE_LIKES)
      .setStatus(Campaign.EnumStatus.VALUE_PAUSED)
      .execute();
    String campaign_id = campaign.getId();

    AdSet adSet = new AdAccount(ad_account_id, context).createAdSet()
      .setName("My AdSet")
      .setOptimizationGoal(AdSet.EnumOptimizationGoal.VALUE_PAGE_LIKES)
      .setBillingEvent(AdSet.EnumBillingEvent.VALUE_IMPRESSIONS)
      .setBidAmount(20L)
      .setPromotedObject("{\"page_id\": " + page_id + "}")
      .setDailyBudget(1000L)
      .setCampaignId(campaign_id)
      .setTargeting(
          new Targeting()
            .setFieldGeoLocations(
              new TargetingGeoLocation()
                .setFieldCountries(Arrays.asList("US"))
            )
        )
      .setStatus(AdSet.EnumStatus.VALUE_PAUSED)
      .execute();
    String ad_set_id = adSet.getId();

    AdCreative creative = new AdAccount(ad_account_id, context).createAdCreative()
      .setName("My Creative")
      .setObjectId(page_id)
      .setTitle("My Page Like Ad")
      .setBody("Like My Page")
      .setImageUrl("http://www.facebookmarketingdevelopers.com/static/images/resource_1.jpg")
      .execute();
    String creative_id = creative.getId();

    Ad ad = new AdAccount(ad_account_id, context).createAd()
      .setName("My Ad")
      .setAdsetId(ad_set_id)
      .setCreative(
          new AdCreative()
            .setFieldId(creative_id)
        )
      .setStatus(Ad.EnumStatus.VALUE_PAUSED)
      .execute();
    String ad_id = ad.getId();

    APINodeList<AdPreview> adPreviews = new Ad(ad_id, context).getPreviews()
      .setAdFormat(AdPreview.EnumAdFormat.VALUE_DESKTOP_FEED_STANDARD)
      .execute();

    adPreviews.forEach(ap -> System.out.println(ap.getFieldBody()));
  }
}
