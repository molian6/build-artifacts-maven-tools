/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.buildartifacts.auth;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.IOException;

// DefaultCredentialProvider fetches credentials from gcloud and falls back to Application Default
// Credentials if that fails.
public class DefaultCredentialProvider implements CredentialProvider {

  private static String[] SCOPES = {"https://www.googleapis.com/auth/cloud-platform",
      "https://www.googleapis.com/auth/cloud-platform.read-only"};

  @Override
  public Credentials getCredential() throws IOException {
    Credentials credentials;
    credentials = GcloudCredentials.tryCreateGcloudCredentials();
    if (credentials != null) {
      return credentials;
    }
    return GoogleCredentials.getApplicationDefault().createScoped(SCOPES);
  }
}
