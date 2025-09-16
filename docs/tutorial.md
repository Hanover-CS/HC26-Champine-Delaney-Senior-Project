---
---
# Tutorial:  
## AI Image Training With Google Colaboratory  
This tutorial will explain how to set up, train, and test an instance of PyTorch AI. Specifically training and testing it in image recognition: to be able to interperate images and visual data in a way that is helpful for us.
### Step 1: Set Up  
  First, sign into Google Colaboratory using a Google account.  
  
  Next, Create a new Notebook through Google Colab or Google Drive. Google Colab is connected to other Google applications on the account and "Notebooks" created can be managed in Google Drive. Notebooks in Google Colab are documents that can hold both text and executable code. Or, in our case, Notebooks allow users to easily access PyTorch AI and Images in our Google Drive in the same document and help us easily export the AI when training is finished.

Add necessary imports at the top of your code cell

We need to import the necessary PyTorch resources before we start using it.   
Here are the imports we need:  
```
import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader
from torchvision import datasets, transforms, models
from google.colab import drive
```
