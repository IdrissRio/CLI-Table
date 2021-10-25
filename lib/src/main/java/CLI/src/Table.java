/* Copyright (c) 2021, Idriss Riouak <idriss.riouak@cs.lth.se> and Momina Rizwan
 * <momina.rizwan@cs.lth.se> All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package CLI.src;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class Table {

  private List<List<String>> table = new ArrayList<>();
  private String separator = "|";
  private String corner = "+";

  public Table(InputStream in) {
    Scanner stdin = new Scanner(System.in);
    int row = 0;
    while (stdin.hasNextLine()) {
      String line = stdin.nextLine();
      String[] tokens = line.split("\\|");
      if (tokens.length <= 1) {
        continue;
      }
      for (int i = 0; i < tokens.length; ++i) {
        this.addToRow(row, tokens[i]);
      }
      row++;
    }
  }

  public Table(String[] headers) {
    for (String column : headers) {
      addToRow(0, column);
    }
  }

  public Table(InputStream in, String separator, String corner) {
    Scanner stdin = new Scanner(System.in);
    int row = 0;
    while (stdin.hasNextLine()) {
      String line = stdin.nextLine();
      String[] tokens = line.split("\\|");
      if (tokens.length <= 1) {
        continue;
      }
      for (int i = 0; i < tokens.length; ++i) {
        this.addToRow(row, tokens[i]);
      }
      row++;
    }
    this.separator = separator;
    this.corner = corner;
  }

  public Table(String[] headers, String separator, String corner) {
    for (String column : headers) {
      addToRow(0, column);
    }
    this.separator = separator;
    this.corner = corner;
  }

  public void filterBy(String column, String value) {
    int size = table.size();
    int removed = 0;
    List<List<String>> res = new ArrayList<>(table);
    for (int i = 1; i < size; i++) {
      if (!table.get(i)
               .get(this.getHeader(column))
               .toLowerCase()
               .trim()
               .equals(value)) {
        res.remove(i - removed);
        removed++;
      }
    }
    table = res;
  }

  public Integer numRow() { return table.size(); }

  public void addRow(String... row) {
    int size = table.size();
    for (String r : row) {
      addToRow(size, r);
    }
  }

  private int getMaxSize(int column) {
    int maxSize = table.get(0).get(column).length();
    for (int data = 0; data < table.size(); data++) {
      if (table.get(data).get(column).length() > maxSize)
        maxSize = table.get(data).get(column).length();
    }
    return maxSize;
  }

  private String formatRow(List<String> row) {
    StringBuilder result = new StringBuilder();
    result.append(" ");
    for (int i = 0; i < row.size(); i++) {
      result.append(StringUtils.center(row.get(i), getMaxSize(i) + 2));
      result.append("|");
    }
    result.append("\n");
    return result.toString();
  }

  private String formatRule() {
    StringBuilder result = new StringBuilder();
    result.append("+");
    for (int i = 0; i < table.get(0).size(); i++) {
      for (int j = 0; j < getMaxSize(i) + 2; j++) {
        result.append("-");
      }
      result.append("+");
    }
    result.append("\n");
    return result.toString();
  }

  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append(formatRule());
    result.append(formatRow(table.get(0)));
    result.append(formatRule());

    for (int row = 1; row < table.size(); row++) {
      result.append(formatRow(table.get(row)));
    }
    result.append(formatRule());
    return result.toString();
  }

  public void addToRow(int row, String entry) {
    if (table.size() <= row) {
      table.add(new ArrayList<String>());
    }
    table.get(row).add(entry);
  }

  public Integer getHeader(String name) {
    for (int i = 0; i < table.get(0).size(); i++) {
      if (table.get(0).get(i).toLowerCase().trim().equals(name)) {
        return i;
      }
    }
    System.err.println("No column found with name: " + name);
    System.exit(1);
    return 0;
  }
}