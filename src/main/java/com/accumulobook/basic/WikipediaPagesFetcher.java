/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.accumulobook.basic;

import com.google.common.base.Joiner;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WikipediaPagesFetcher {

  public static InputStream fetch(String... pages) throws MalformedURLException, IOException {

    String pagesString = Joiner.on("%0A").join(pages);
    
    URL url = new URL("https://en.wikipedia.org/w/index.php?title=Special:Export&pages=" + pagesString + "&curonly&action=submit");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setRequestMethod("POST");

    conn.setDoOutput(true);
    conn.getOutputStream().write("".getBytes("UTF-8"));
    conn.getOutputStream().flush();

    int responseCode = conn.getResponseCode();
    System.out.println(responseCode);

    return conn.getInputStream();
  }
}
