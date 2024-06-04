# Feature Flag Demo with ConfigCat

- [ConfigCat](https://configcat.com/) adalah salah satu solusi untuk manajemen feature flag.
  - Saya pilih ini karena configcat termasuk yang paling mudah dipakai, selain UI nya yang bagus, 
    fitur dan dokumentasinya juga sangat mudah dimengerti.
  - Server ConfigCat pun sangat mudah, karena cukup signup maka kamu langsung bisa pakai di CDN server nya.
- Contoh lainnya adalah: 
  - [Posthog](https://posthog.com/) lebih kaya di fitur-fitur nya, bisa hosting sendiri
  - [Flagsmith](https://www.flagsmith.com/)
  - [Unleash](https://www.getunleash.io/) 

## Apa itu feature-flag

Feature flag itu hanya sebuah toggle on/off buat fitur aplikasi, yang biasanya merupakan fitur baru. 
Jadi misal fitur UI baru bisa di switch on/off kapan saja.
Mungkin kalian pernah mengalami layout di facebook tiba2 berubah
di teman kalian tapi waktu kalian cek di tempat kalian, dia belum berubah layout nya.

Lalu setelah berapa hari tiba-tiba semua orang kembali ke layout lama.
Selain dengan sistem "Canary Deployment" hal itu juga bisa dilakukan dengan memakai feature flag.
Jadi fitur mu bisa hotswitch dan dikontrol oleh aplikasi.

### Flowchart

```mermaid

flowchart TD
    ff_server["Feature Flag Server (ConfigCat)"] <-- Fetch feature config --> fe["Frontends"] & be["Backend"]
    be -- Get feature flag state --> bem["Backend Memory"]
    fe -- Get feature flag state --> fem["Frontends Memory"]
```

## Usecase

### Initial Application

Kita memiliki aplikasi untuk menghitung harga penjualan makanan di restaurant.
Aplikasi itu akan menghitung harga subtotal makanan + biasa servis + pajak.

#### Initial Flowchart:

```mermaid

flowchart TD
    subgraph calc_subtotal["Subtotal"]
        calc_items("Hitung harga makanan")
        calc_fees("Hitung biaya service")
        calc_tax_ff("Hitung pajak 5%")
    end
    start["Start"] --> calc_subtotal
    calc_items --> calc_fees & calc_tax_ff
    calc_subtotal --> calc_total("Hitung total harga")
```

### Request Changes

Perhitungan pajak akan berubah pada tahun 2024.
Rollout perubahan perhitungan pajak harus smooth dan bisa di rollback kapan saja.

```mermaid
flowchart TD
    subgraph calc_tax_ff["Pajak dengan Feature Flag Check"]
        calc_tax("Pilih Hitung Pajak")
        enable_tax_24{"Cek pajak 2024 aktif?"}
        calc_tax_pre24("Hitung pajak 5%")
        calc_tax_24("Hitung pajak 5% + 2%")
    end
    subgraph calc_subtotal["Subtotal"]
        calc_items("Hitung harga makanan")
        calc_fees("Hitung biaya service")
        calc_tax_ff
    end
    start["Start"] --> calc_subtotal
    calc_items --> calc_fees & calc_tax_ff
    calc_subtotal --> calc_total("Hitung total harga")
    calc_tax --> enable_tax_24
    enable_tax_24 -- Tidak --> calc_tax_pre24
    enable_tax_24 -- Ya --> calc_tax_24
```

#### _Quiz Time_

_Bila ini aplikasi dengan model OOP, 
design pattern apa yang dipakai untuk pemilihan fitur di atas?_